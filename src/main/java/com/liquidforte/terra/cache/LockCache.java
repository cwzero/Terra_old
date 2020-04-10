package com.liquidforte.terra.cache;

import com.google.inject.Inject;
import com.liquidforte.terra.client.TerraClient;
import com.liquidforte.terra.database.LockDatabase;
import com.liquidforte.terra.model.lock.FileLock;

public class LockCache extends AbstractCache<LockDatabase> implements ILockCache {
	private ModCache modCache;
	private IFileCache fileCache;

	@Inject
	public LockCache(TerraClient client, LockDatabase database, ModCache modCache, IFileCache fileCache) {
		super(client, database);
		this.modCache = modCache;
		this.fileCache = fileCache;
	}

	@Override
	public long getLock(String slug, String filter) {
		long addonId = modCache.getAddonId(slug);

		if (addonId <= 0) {
			throw new RuntimeException("Couldn't find addon id for slug: " + slug);
		} else {
			return getLock(addonId, filter);
		}
	}

	@Override
	public long getLock(long addonId, String filter) {
		long fileId = getDatabase().getLock(addonId, filter);

		if (fileId <= 0) {
			FileLock lock = getClient().getLatestFile(addonId, filter);
			fileId = lock.getFileId();
			if (addonId > 0 && fileId > 0) {
				getDatabase().lock(addonId, filter, fileId);
			}
		}

		if (fileId <= 0) {
			throw new RuntimeException("Couldn't find file id or addon id (addonId:fileId) " + addonId + ":" + fileId);
		}

		return fileId;
	}

	@Override
	public long update(long addonId, String filter) {
		FileLock lock = getClient().getLatestFile(addonId);
		long fileId = lock.getFileId();
		if (addonId != -1 && fileId != -1) {
			getDatabase().lock(addonId, filter, fileId);
		}
		return fileId;
	}

	@Override
	public long update(String slug, String filter) {
		return update(modCache.getAddonId(slug), filter);
	}

	@Override
	public FileLock getFile(long addonId, String filter) {
		return fileCache.getFile(addonId, getLock(addonId, filter));
	}

	@Override
	public FileLock getFile(String slug, String filter) {
		return fileCache.getFile(modCache.getAddonId(slug), getLock(slug, filter));
	}
}

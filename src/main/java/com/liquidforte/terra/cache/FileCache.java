package com.liquidforte.terra.cache;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.inject.Inject;
import com.liquidforte.terra.client.TerraClient;
import com.liquidforte.terra.database.CacheDatabase;
import com.liquidforte.terra.database.DependencyDao;
import com.liquidforte.terra.model.lock.FileDependency;
import com.liquidforte.terra.model.lock.FileLock;
import com.liquidforte.terra.util.DownloadUtil;
import com.liquidforte.terra.util.FingerprintUtil;

public class FileCache extends AbstractCache<CacheDatabase> implements IFileCache {
	private static Path CACHE_PATH = Paths.get(System.getProperty("user.home") + "/.terra/cache");
	private ILockCache lockCache;

	@Inject
	public FileCache(TerraClient client, CacheDatabase database, ILockCache lockCache) {
		super(client, database);
		this.lockCache = lockCache;
	}

	@Override
	public FileLock getFile(long addonId, long fileId) {
		FileLock lock = getDatabase().getFile(addonId, fileId);

		if (lock == null) {
			lock = getClient().getFile(addonId, fileId);
			lock.setAddonId(addonId);
			if (addonId != -1 && fileId != -1) {
				getDatabase().saveFile(lock);
			}
		} else {
			lock.setAddonId(addonId);
		}

		return lock;
	}

	public static Path getDestDir(long addonId, long fileId) {
		return CACHE_PATH.resolve(addonId + "").resolve(fileId + "");
	}

	@Override
	public Path getCachePath(long addonId, long fileId) {
		FileLock lock = getFile(addonId, fileId);
		return getDestDir(addonId, fileId).resolve(lock.getFileName());
	}

	@Override
	public void downloadFileToCache(long addonId, long fileId) throws IOException {
		downloadFileDirect(getDestDir(addonId, fileId), addonId, fileId);
	}

	@Override
	public void installFile(Path destDir, long addonId, long fileId) throws IOException {
		FileLock file = getFile(addonId, fileId);		
		Path destFile = destDir.resolve(file.getFileName());

		for (FileDependency dep : getDependencies(addonId, fileId)) {
			long depAddonId = dep.getAddonId();
			long depFileId = lockCache.getLock(depAddonId, "true");
			
			if (depAddonId > 0 && depFileId > 0) {
				installFile(destDir, dep.getAddonId(), depFileId);
			}
		}

		if (destFile.toFile().exists()) {
			if (file.getFingerprint() > 0) {
				long check = FingerprintUtil.getFileHash(destFile);
				if (check == file.getFingerprint())
					return;
			}
		}

		downloadFileToCache(addonId, fileId);

		try {
			FileUtils.copyFile(getCachePath(addonId, fileId).toFile(), destFile.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<FileDependency> getDependencies(long addonId, long fileId) {
		FileLock lock = getFile(addonId, fileId);
		
		try {
			getDatabase().withJdbiExtension(DependencyDao.class, dao -> dao.getDependencies(addonId)).forEach(d -> {
				if (!lock.getDependencies().stream().anyMatch(dep -> dep.getAddonId() == d.getAddonId())) {
					lock.getDependencies().add(d);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lock.getDependencies();
	}

	@Override
	public void downloadFileDirect(Path destDir, long addonId, long fileId) throws IOException {
		FileLock lock = getFile(addonId, fileId);

		Path destPath = destDir.resolve(lock.getFileName());

		if (destPath.toFile().exists()) {
			if (lock.getFingerprint() > 0) {
				long check = FingerprintUtil.getFileHash(destPath);
				if (check == lock.getFingerprint())
					return;
			}
		}

		try {
			DownloadUtil.downloadMod(destDir, lock);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

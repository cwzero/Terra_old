package com.liquidforte.terra.command;

import java.io.IOException;

import com.google.inject.Inject;
import com.liquidforte.terra.cache.IFileCache;
import com.liquidforte.terra.cache.ILockCache;
import com.liquidforte.terra.cache.ModCache;
import com.liquidforte.terra.loader.GroupLoader;
import com.liquidforte.terra.mode.Mode;
import com.liquidforte.terra.model.Group;
import com.liquidforte.terra.model.ModSpec;

public class DownloadCommand extends AbstractCommand {
	protected GroupLoader loader;
	protected IFileCache fileCache;
	protected ModCache modCache;
	protected ILockCache lockCache;

	@Inject
	public DownloadCommand(Mode mode, GroupLoader loader, IFileCache fileCache, ModCache modCache, ILockCache lockCache) {
		super(mode);
		this.loader = loader;
		this.fileCache = fileCache;
		this.modCache = modCache;
		this.lockCache = lockCache;
	}

	@Override
	public Object apply(Mode mode) {
		for (Group group : loader.loadGroups()) {
			for (ModSpec spec : group.getMods()) {
				long addonId = modCache.getAddonId(spec.getSlug());
				long fileId = lockCache.getLock(spec.getSlug(), spec.getFilter());
				try {
					fileCache.downloadFileToCache(addonId, fileId);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Downloaded");
		return null;
	}
}

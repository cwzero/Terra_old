package com.liquidforte.terra.command;

import java.io.IOException;

import com.google.inject.Inject;
import com.liquidforte.terra.cache.IFileCache;
import com.liquidforte.terra.cache.ILockCache;
import com.liquidforte.terra.cache.ModCache;
import com.liquidforte.terra.config.AppOptions;
import com.liquidforte.terra.loader.GroupLoader;
import com.liquidforte.terra.mode.Mode;
import com.liquidforte.terra.model.Group;
import com.liquidforte.terra.model.ModSpec;

public class InstallCommand extends CommandBase {
	protected GroupLoader loader;
	protected IFileCache fileCache;
	protected ModCache modCache;
	protected ILockCache lockCache;
	protected AppOptions options;

	@Inject
	public InstallCommand(Mode mode, GroupLoader loader, IFileCache fileCache, ModCache modCache, ILockCache lockCache,
			AppOptions options) {
		super(mode, loader, fileCache, modCache, lockCache, options);
	}

	@Override
	public Object apply(Mode mode) {
		options.getModsDir().toFile().mkdirs();

		for (Group group : loader.loadGroups()) {
			for (ModSpec spec : group.getMods()) {
				long addonId = modCache.getAddonId(spec.getSlug());
				long fileId = lockCache.getLock(spec.getSlug(), spec.getFilter());
				try {
					fileCache.installFile(options.getModsDir(), addonId, fileId);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Installed");
		return null;
	}
}

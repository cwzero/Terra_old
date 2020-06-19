package com.liquidforte.terra.command;

import com.google.inject.Inject;
import com.liquidforte.terra.cache.IFileCache;
import com.liquidforte.terra.cache.ILockCache;
import com.liquidforte.terra.cache.ModCache;
import com.liquidforte.terra.loader.GroupLoader;
import com.liquidforte.terra.mode.Mode;
import com.liquidforte.terra.model.Group;
import com.liquidforte.terra.model.ModSpec;

public class ResolveCommand extends CommandBase {
	@Inject
	public ResolveCommand(Mode mode, GroupLoader loader, IFileCache fileCache, ModCache modCache, ILockCache lockCache) {
		super(mode, loader, fileCache, modCache, lockCache);
	}

	@Override
	public Object apply(Mode t) {
		System.out.println("Resolved");

		for (Group group : loader.loadGroups()) {
			for (ModSpec spec : group.getMods()) {
				lockCache.getLock(spec.getSlug(), spec.getFilter());
			}
		}

		return null;
	}
}

package com.liquidforte.terra.command;

import com.google.inject.Inject;
import com.liquidforte.terra.cache.ILockCache;
import com.liquidforte.terra.mode.Mode;

public class UpdateCommand extends AbstractCommand {
	private ILockCache lockCache;
	
	@Inject
	public UpdateCommand(Mode mode, ILockCache lockCache) {
		super(mode);
		this.lockCache = lockCache;
	}

	@Override
	public Object apply(Mode t) {
		lockCache.updateAll();
		
		return null;
	}
}

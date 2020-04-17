package com.liquidforte.terra.mode;

import com.google.inject.Inject;
import com.liquidforte.terra.cache.ILockCache;
import com.liquidforte.terra.command.CommandParser;
import com.liquidforte.terra.database.CacheDatabase;
import com.liquidforte.terra.database.LockDatabase;

public class DefaultAppMode extends AppMode {
	private ILockCache lockCache;
	
	@Inject
	public DefaultAppMode(CacheDatabase cacheDatabase, LockDatabase lockDatabase, CommandParser commands,
			ILockCache lockCache) {
		super(cacheDatabase, lockDatabase, commands);
		this.lockCache = lockCache;
	}

	@Override
	public void init() {
		super.init();

		// TODO: initialize
		lockCache.load();
	}

	@Override
	public void doRun() {
		// TODO: implement
		
		super.doRun();
		
		lockCache.save();
	}
}

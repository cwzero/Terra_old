package com.liquidforte.terra.mode;

import com.google.inject.Inject;
import com.liquidforte.terra.command.CommandParser;
import com.liquidforte.terra.database.CacheDatabase;
import com.liquidforte.terra.database.LockDatabase;

public class DefaultAppMode extends AppMode {	
	@Inject
	public DefaultAppMode(CacheDatabase cacheDatabase, LockDatabase lockDatabase, CommandParser commands) {
		super(cacheDatabase, lockDatabase, commands);
	}

	@Override
	public void init() {
		super.init();

		// TODO: initialize
	}

	@Override
	public void doRun() {
		// TODO: implement
		
		super.doRun();
	}
}

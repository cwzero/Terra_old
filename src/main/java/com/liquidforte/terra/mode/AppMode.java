package com.liquidforte.terra.mode;

import com.liquidforte.terra.command.CommandParser;
import com.liquidforte.terra.database.CacheDatabase;
import com.liquidforte.terra.database.LockDatabase;

public abstract class AppMode implements Mode {
	protected CacheDatabase cacheDatabase;
	protected LockDatabase lockDatabase;
	protected CommandParser commands;

	public AppMode(CacheDatabase cacheDatabase, LockDatabase lockDatabase, CommandParser commands) {
		this.cacheDatabase = cacheDatabase;
		this.lockDatabase = lockDatabase;
		this.commands = commands;
	}

	@Override
	public void runCommand(String command) {
		commands.run(command);
	}

	@Override
	public void init() {
		cacheDatabase.init();
		lockDatabase.init();
	}

	@Override
	public void doRun() {
		commands.run();
	}
}

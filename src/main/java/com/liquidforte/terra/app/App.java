package com.liquidforte.terra.app;

import com.google.inject.Inject;
import com.liquidforte.terra.database.DatabaseServer;
import com.liquidforte.terra.mode.Mode;

public class App {
	private final DatabaseServer databaseServer;
	private Mode mode;

	@Inject
	public App(DatabaseServer databaseServer, Mode mode) {
		this.databaseServer = databaseServer;
		this.mode = mode;
	}

	public void run() {
		try (databaseServer) {
			databaseServer.start();

			mode.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

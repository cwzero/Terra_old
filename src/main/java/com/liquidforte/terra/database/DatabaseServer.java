package com.liquidforte.terra.database;

import java.sql.SQLException;

import org.h2.tools.Server;

import com.google.inject.Inject;

public class DatabaseServer implements AutoCloseable {
	private Server server;
	private boolean started = false;

	@Inject
	public DatabaseServer(Server server) {
		this.server = server;
	}

	public boolean isStarted() {
		return !server.getStatus().trim().toLowerCase().contentEquals("not started");
	}

	public void start() {
		if (!started && !isStarted()) {
			try {
				server.start();
				started = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void close() throws Exception {
		if (started && isStarted()) {
			server.stop();
		}
	}
}

package com.liquidforte.terra.config;

public class LockConfig extends DatabaseConfig {
	private String url = "jdbc:h2:tcp://localhost/mem:lock";

	@Override
	public String getUrl() {
		return url;
	}

	public void setDatabaseUrl(String url) {
		this.url = url;
	}
}

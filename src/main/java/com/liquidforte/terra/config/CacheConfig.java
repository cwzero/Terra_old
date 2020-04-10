package com.liquidforte.terra.config;

public class CacheConfig extends DatabaseConfig {
	private String url = "jdbc:h2:tcp://localhost/~/.terra/db/cache";

	@Override
	public String getUrl() {
		return url;
	}

	public void setDatabaseUrl(String url) {
		this.url = url;
	}
}

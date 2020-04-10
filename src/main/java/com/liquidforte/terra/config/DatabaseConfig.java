package com.liquidforte.terra.config;

public abstract class DatabaseConfig {
	private String username = "sa";
	private String password = "sa";
	
	public abstract String getUrl();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

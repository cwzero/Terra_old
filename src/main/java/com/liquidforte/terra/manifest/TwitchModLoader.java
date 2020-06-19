package com.liquidforte.terra.manifest;

public class TwitchModLoader {
	private String id;
	private boolean primary = false;

	public TwitchModLoader(String id, boolean primary) {
		this.id = id;
		this.primary = primary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
}

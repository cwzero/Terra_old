package com.liquidforte.terra.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private String id = "";
	private boolean enabled = true;
	private List<ModSpec> mods = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<ModSpec> getMods() {
		return mods;
	}

	public void setMods(List<ModSpec> mods) {
		this.mods = mods;
	}
}

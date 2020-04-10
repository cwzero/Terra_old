package com.liquidforte.terra.model.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PackLock {
	private Map<String, ModLock> mods = new HashMap<String, ModLock>();

	public PackLock() {

	}

	public Map<String, ModLock> getMods() {
		return mods;
	}

	public void setMods(Map<String, ModLock> mods) {
		this.mods = mods;
	}

	public Set<String> keys() {
		return getMods().keySet();
	}

	public ModLock getMod(String slug) {
		return getMods().get(slug);
	}
}

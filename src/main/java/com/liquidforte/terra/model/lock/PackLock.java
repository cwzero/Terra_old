package com.liquidforte.terra.model.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PackLock {
	private Map<Long, Map<String, Long>> mods = new HashMap<Long, Map<String, Long>>();

	public PackLock() {

	}

	public long get(long addonId, String filter) {
		return mods.get(addonId).get(filter);
	}

	public void set(long addonId, String filter, Long lock) {
		if (!mods.containsKey(addonId)) {
			mods.put(addonId, new HashMap<String, Long>());
		}

		mods.get(addonId).put(filter, lock);
	}

	public Map<Long, Map<String, Long>> getMods() {
		return mods;
	}

	public void setMods(Map<Long, Map<String, Long>> mods) {
		this.mods = mods;
	}

	public Set<Long> keys() {
		return getMods().keySet();
	}

	public Set<String> filters(long addonId) {
		return getMods().get(addonId).keySet();
	}

	public Long getLock(long addonId, String filter) {
		return getMods().get(addonId).get(filter);
	}
}

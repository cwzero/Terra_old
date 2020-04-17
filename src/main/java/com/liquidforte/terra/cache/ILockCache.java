package com.liquidforte.terra.cache;

import com.liquidforte.terra.model.lock.FileLock;

public interface ILockCache {

	long getLock(String slug, String filter);

	long getLock(long addonId, String filter);

	long update(long addonId, String filter);

	long update(String slug, String filter);

	FileLock getFile(long addonId, String filter);

	FileLock getFile(String slug, String filter);
	
	void load();
	
	void save();

	void updateAll();
}
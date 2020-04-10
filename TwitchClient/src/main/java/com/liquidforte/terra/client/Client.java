package com.liquidforte.terra.client;

import com.liquidforte.terra.model.lock.FileLock;
import com.liquidforte.terra.model.lock.ModLock;

public interface Client {
	ModLock getMod(String slug);

	ModLock getMod(long addonId);
	
	default FileLock getLatestFile(String slug) {
		return getLatestFile(getMod(slug).getAddonId());
	}

	FileLock getLatestFile(long addonId);
	
	FileLock getLatestFile(long addonId, String fileNameFilter);

	FileLock getFile(long addonId, long fileId);
}

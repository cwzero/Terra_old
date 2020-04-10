package com.liquidforte.terra.client;

import com.liquidforte.terra.model.lock.FileLock;

public abstract class AbstractClient implements Client {
	@Override
	public FileLock getLatestFile(String slug) {
		return getLatestFile(getMod(slug).getAddonId());
	}
}

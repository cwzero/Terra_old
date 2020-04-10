package com.liquidforte.terra.client;

import com.google.inject.Inject;
import com.liquidforte.terra.model.lock.FileLock;
import com.liquidforte.terra.model.lock.ModLock;
import com.liquidforte.terra.twitch.TwitchClient;

public class TerraClient extends AbstractClient {
	private TwitchClient twitchClient;

	@Inject
	public TerraClient(TwitchClient twitchClient) {
		this.twitchClient = twitchClient;
	}

	@Override
	public ModLock getMod(String slug) {
		return twitchClient.getMod(slug);
	}

	@Override
	public ModLock getMod(long addonId) {
		return twitchClient.getMod(addonId);
	}

	@Override
	public FileLock getLatestFile(long addonId) {
		return twitchClient.getLatestFile(addonId);
	}

	@Override
	public FileLock getFile(long addonId, long fileId) {
		return twitchClient.getFile(addonId, fileId);
	}

	@Override
	public FileLock getLatestFile(long addonId, String fileNameFilter) {
		return twitchClient.getLatestFile(addonId, fileNameFilter);
	}
}

package com.liquidforte.terra.twitch;

import java.util.ArrayList;
import java.util.List;

public class FingerprintMatch {
	private long id;
	private TwitchFile file;
	private List<TwitchFile> latestFiles = new ArrayList<TwitchFile>();

	public List<TwitchFile> getLatestFiles() {
		return latestFiles;
	}

	public void setLatestFiles(List<TwitchFile> latestFiles) {
		this.latestFiles = latestFiles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TwitchFile getFile() {
		return file;
	}

	public void setFile(TwitchFile file) {
		this.file = file;
	}
}

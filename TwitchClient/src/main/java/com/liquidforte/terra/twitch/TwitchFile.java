package com.liquidforte.terra.twitch;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liquidforte.terra.model.FileBase;

public class TwitchFile extends FileBase {
	private List<String> gameVersion = new ArrayList<String>();
	private List<TwitchFileDependency> dependencies = new ArrayList<TwitchFileDependency>();

	public List<String> getGameVersion() {
		return gameVersion;
	}

	public void setGameVersion(List<String> gameVersion) {
		this.gameVersion = gameVersion;
	}

	public List<TwitchFileDependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<TwitchFileDependency> dependencies) {
		this.dependencies = dependencies;
	}
	
	@Override
	@JsonProperty("id")
	public void setFileId(long fileId) {
		super.setFileId(fileId);
	}
	
	@Override
	@JsonProperty("packageFingerprint")
	public void setFingerprint(long fingerprint) {
		super.setFingerprint(fingerprint);
	}
}

package com.liquidforte.terra.manifest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitchManifestFile {
	private long addonId = 0;
	private long fileId = 0;
	private boolean required = true;

	public TwitchManifestFile(long addonId, long fileId) {
		this.addonId = addonId;
		this.fileId = fileId;
	}

	@JsonProperty("projectID")
	public long getAddonId() {
		return addonId;
	}

	@JsonProperty("projectID")
	public void setAddonId(long addonId) {
		this.addonId = addonId;
	}

	@JsonProperty("fileID")
	public long getFileId() {
		return fileId;
	}

	@JsonProperty("fileID")
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
}

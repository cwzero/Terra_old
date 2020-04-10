package com.liquidforte.terra.model.lock;

public class FileDependency {
	private long sourceAddonId = -1;
	private long addonId = -1;

	public long getSourceAddonId() {
		return sourceAddonId;
	}

	public void setSourceAddonId(long sourceAddonId) {
		this.sourceAddonId = sourceAddonId;
	}

	public long getAddonId() {
		return addonId;
	}

	public void setAddonId(long addonId) {
		this.addonId = addonId;
	}
}

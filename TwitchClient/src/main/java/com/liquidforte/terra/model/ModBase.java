package com.liquidforte.terra.model;

public abstract class ModBase {
	private long addonId = -1;
	private String slug = "";

	public long getAddonId() {
		return addonId;
	}

	public void setAddonId(long addonId) {
		this.addonId = addonId;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
}

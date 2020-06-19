package com.liquidforte.terra.config;

public class AppConfig {
	private String packName;
	private String packVersion;
	private String packAuthor;
	private String twitchOverride = "override";
	private String minecraftVersion = "1.12.2";
	private String forgeVersion = "14.23.5.2854";
	private String manifestPath = "build/manifest.json";

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public String getPackVersion() {
		return packVersion;
	}

	public void setPackVersion(String packVersion) {
		this.packVersion = packVersion;
	}

	public String getPackAuthor() {
		return packAuthor;
	}

	public void setPackAuthor(String packAuthor) {
		this.packAuthor = packAuthor;
	}

	public String getTwitchOverride() {
		return twitchOverride;
	}

	public void setTwitchOverride(String twitchOverride) {
		this.twitchOverride = twitchOverride;
	}

	public String getMinecraftVersion() {
		return minecraftVersion;
	}

	public void setMinecraftVersion(String minecraftVersion) {
		this.minecraftVersion = minecraftVersion;
	}

	public String getForgeVersion() {
		return forgeVersion;
	}

	public void setForgeVersion(String forgeVersion) {
		this.forgeVersion = forgeVersion;
	}

	public String getManifestPath() {
		return manifestPath;
	}

	public void setManifestPath(String manifestPath) {
		this.manifestPath = manifestPath;
	}
}

package com.liquidforte.terra.manifest;

import java.util.ArrayList;
import java.util.List;

public class TwitchManifest {
	private List<TwitchManifestFile> files = new ArrayList<>();
	private String manifestType = "minecraftModpack";
	private long manifestVersion = 1;
	private String name;
	private String version;
	private String author;
	private String override = "override";
	private TwitchMinecraft minecraft;

	public TwitchManifest(String name, String version, String author, TwitchMinecraft minecraft) {
		this(name, version, author);
		this.minecraft = minecraft;
	}

	public TwitchManifest(String name, String version, String author, String override, TwitchMinecraft minecraft) {
		this(name, version, author, override);
		this.minecraft = minecraft;
	}

	public TwitchManifest(String name, String version, String author) {
		this.name = name;
		this.version = version;
		this.author = author;
	}

	public TwitchManifest(String name, String version, String author, String override) {
		this(name, version, author);
		this.override = override;
	}

	public List<TwitchManifestFile> getFiles() {
		return files;
	}

	public void setFiles(List<TwitchManifestFile> files) {
		this.files = files;
	}

	public String getManifestType() {
		return manifestType;
	}

	public void setManifestType(String manifestType) {
		this.manifestType = manifestType;
	}

	public long getManifestVersion() {
		return manifestVersion;
	}

	public void setManifestVersion(long manifestVersion) {
		this.manifestVersion = manifestVersion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOverride() {
		return override;
	}

	public void setOverride(String override) {
		this.override = override;
	}

	public TwitchMinecraft getMinecraft() {
		return minecraft;
	}

	public void setMinecraft(TwitchMinecraft minecraft) {
		this.minecraft = minecraft;
	}
}

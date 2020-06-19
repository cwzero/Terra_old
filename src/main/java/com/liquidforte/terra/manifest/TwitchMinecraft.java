package com.liquidforte.terra.manifest;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.liquidforte.terra.config.AppConfig;
import com.liquidforte.terra.config.AppOptions;

public class TwitchMinecraft {
	private String version;
	private List<TwitchModLoader> modLoaders = new ArrayList<>();
	
	public TwitchMinecraft(String minecraftVersion, String forgeVersion) {
		this.version = minecraftVersion;
		modLoaders.add(new TwitchForge(forgeVersion));
	}

	@Inject
	public TwitchMinecraft(AppConfig config, AppOptions options) {
		String defMCVer = config.getMinecraftVersion();
		String defFVer = config.getForgeVersion();
		
		String minecraftVersion = options.getMinecraftVersion(defMCVer);
		String forgeVersion = options.getForgeVersion(defFVer);

		this.version = minecraftVersion;
		modLoaders.add(new TwitchForge(forgeVersion));
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<TwitchModLoader> getModLoaders() {
		return modLoaders;
	}

	public void setModLoaders(List<TwitchModLoader> modLoaders) {
		this.modLoaders = modLoaders;
	}
}

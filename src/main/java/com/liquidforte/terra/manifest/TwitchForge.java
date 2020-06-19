package com.liquidforte.terra.manifest;

public class TwitchForge extends TwitchModLoader {
	// TODO: get latest forge version for mc version
	public TwitchForge(String forgeVersion) {
		super("forge-" + forgeVersion, true);
	}
}

package com.liquidforte.terra.twitch;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liquidforte.terra.model.ModBase;

public class TwitchAddon extends ModBase {
	@Override
	@JsonProperty("id")
	public void setAddonId(long addonId) {
		super.setAddonId(addonId);
	}
}

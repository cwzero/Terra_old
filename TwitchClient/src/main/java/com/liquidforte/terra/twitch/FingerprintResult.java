package com.liquidforte.terra.twitch;

import java.util.ArrayList;
import java.util.List;

public class FingerprintResult {
	private List<FingerprintMatch> exactMatches = new ArrayList<>();

	public List<FingerprintMatch> getExactMatches() {
		return exactMatches;
	}

	public void setExactMatches(List<FingerprintMatch> exactMatches) {
		this.exactMatches = exactMatches;
	}
}

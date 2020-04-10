package com.liquidforte.terra.twitch;

import java.util.List;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface Twitch {
	@RequestLine("GET /addon/{addonId}")
	TwitchAddon getAddon(@Param("addonId") long id);

	@RequestLine("GET /addon/{addonId}/file/{fileId}")
	TwitchFile getFile(@Param("addonId") long addonId, @Param("fileId") long fileId);

	@RequestLine("GET /addon/{addonId}/files")
	List<TwitchFile> getFiles(@Param("addonId") long addonId);

	@RequestLine("GET /addon/search?categoryId={categoryId}&gameId={gameId}&gameVersion={gameVersion}&index={index}&pageSize={pageSize}&searchFilter={searchFilter}&sectionId={sectionId}&sort={sort}")
	List<TwitchAddon> addonSearch(@Param("categoryId") long categoryId, @Param("gameId") long gameId,
			@Param("gameVersion") String gameVersion, @Param("index") long index, @Param("pageSize") long pageSize,
			@Param("searchFilter") String searchFilter, @Param("sectionId") long sectionId, @Param("sort") long sort);

	@RequestLine("GET /addon/{addonId}/file/{fileId}/download-url")
	String getDownloadUrl(@Param("addonId") long addonId, @Param("fileId") long fileId);

	@RequestLine("POST /fingerprint")
	@Headers({"Content-Type: application/json", "Accept: application/json"})
	FingerprintResult fingerprint(Long... fingerprints);
}

package com.liquidforte.terra.cache;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.liquidforte.terra.model.lock.FileDependency;
import com.liquidforte.terra.model.lock.FileLock;

public interface IFileCache {

	FileLock getFile(long addonId, long fileId);

	Path getCachePath(long addonId, long fileId);

	void downloadFileToCache(long addonId, long fileId) throws IOException;

	void installFile(Path destDir, long addonId, long fileId) throws IOException;

	List<FileDependency> getDependencies(long addonId, long fileId);

	void downloadFileDirect(Path destDir, long addonId, long fileId) throws IOException;

}
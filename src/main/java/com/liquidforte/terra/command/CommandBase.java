package com.liquidforte.terra.command;

import com.liquidforte.terra.cache.IFileCache;
import com.liquidforte.terra.cache.ILockCache;
import com.liquidforte.terra.cache.ModCache;
import com.liquidforte.terra.config.AppConfig;
import com.liquidforte.terra.config.AppOptions;
import com.liquidforte.terra.loader.GroupLoader;
import com.liquidforte.terra.mode.Mode;

public abstract class CommandBase extends AbstractCommand {
	protected GroupLoader loader;
	protected IFileCache fileCache;
	protected ModCache modCache;
	protected ILockCache lockCache;
	protected AppOptions options;
	protected AppConfig config;

	public CommandBase(Mode mode, GroupLoader loader, IFileCache fileCache, ModCache modCache, ILockCache lockCache,
			AppOptions options, AppConfig config) {
		this(mode, loader, fileCache, modCache, lockCache, options);
		this.config = config;
	}

	public CommandBase(Mode mode, GroupLoader loader, IFileCache fileCache, ModCache modCache, ILockCache lockCache) {
		this(mode, lockCache);
		this.loader = loader;
		this.fileCache = fileCache;
		this.modCache = modCache;
	}

	public CommandBase(Mode mode, GroupLoader loader, IFileCache fileCache, ModCache modCache, ILockCache lockCache,
			AppOptions options) {
		this(mode, loader, fileCache, modCache, lockCache);
		this.options = options;
	}

	public CommandBase(Mode mode, ILockCache lockCache) {
		super(mode);
		this.lockCache = lockCache;
	}

	public GroupLoader getLoader() {
		return loader;
	}

	public IFileCache getFileCache() {
		return fileCache;
	}

	public ModCache getModCache() {
		return modCache;
	}

	public ILockCache getLockCache() {
		return lockCache;
	}

	public AppOptions getOptions() {
		return options;
	}

	public AppConfig getConfig() {
		return config;
	}

	public void setConfig(AppConfig config) {
		this.config = config;
	}

	public void setLoader(GroupLoader loader) {
		this.loader = loader;
	}

	public void setFileCache(IFileCache fileCache) {
		this.fileCache = fileCache;
	}

	public void setModCache(ModCache modCache) {
		this.modCache = modCache;
	}

	public void setLockCache(ILockCache lockCache) {
		this.lockCache = lockCache;
	}

	public void setOptions(AppOptions options) {
		this.options = options;
	}
}

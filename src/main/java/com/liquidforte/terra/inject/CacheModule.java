package com.liquidforte.terra.inject;

import com.google.inject.AbstractModule;
import com.liquidforte.terra.cache.FileCache;
import com.liquidforte.terra.cache.IFileCache;
import com.liquidforte.terra.cache.ILockCache;
import com.liquidforte.terra.cache.LockCache;
import com.liquidforte.terra.cache.ModCache;

public class CacheModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(IFileCache.class).to(FileCache.class);
		bind(ILockCache.class).to(LockCache.class).asEagerSingleton();
		bind(ModCache.class);
	}
}

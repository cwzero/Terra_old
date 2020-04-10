package com.liquidforte.terra.inject;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.liquidforte.terra.app.App;
import com.liquidforte.terra.config.AppConfig;
import com.liquidforte.terra.config.AppOptions;
import com.liquidforte.terra.config.CacheConfig;
import com.liquidforte.terra.mode.AppMode;
import com.liquidforte.terra.mode.Mode;

public class AppModule extends AbstractModule {
	private AppOptions options;

	public AppModule(AppOptions options) {
		this.options = options;
	}

	@Override
	protected void configure() {
		bind(AppOptions.class).toInstance(options);
		bind(App.class).in(Singleton.class);
		bind(Mode.class).to(AppMode.class);
	}

	/*
	 * @Provides
	 * 
	 * @Singleton public AppOptions getAppOptions(Injector injector) {
	 * injector.injectMembers(options); return options; }
	 */

	@Provides
	@Singleton
	public AppConfig getAppConfig(ObjectMapper mapper, AppOptions options) {
		File appConfigFile = new File(options.getAppConfigPath());

		try {
			return mapper.readValue(appConfigFile, AppConfig.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new AppConfig();
	}

	@Provides
	@Singleton
	public CacheConfig getCacheConfig(ObjectMapper mapper, AppOptions options) {
		CacheConfig s = new CacheConfig();
		File cacheConfigFile = new File(options.getCacheConfigPath());

		if (cacheConfigFile.exists()) {
			try {
				s = mapper.readerForUpdating(s).readValue(cacheConfigFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return s;
	}
}

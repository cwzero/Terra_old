package com.liquidforte.terra.inject;

import com.google.inject.Singleton;
import com.liquidforte.terra.config.AppOptions;
import com.liquidforte.terra.mode.AppMode;
import com.liquidforte.terra.mode.DefaultAppMode;

public class DefaultAppModule extends AppModule {
	public DefaultAppModule(AppOptions options) {
		super(options);
	}

	@Override
	protected void configure() {
		super.configure();

		bind(AppMode.class).to(DefaultAppMode.class).in(Singleton.class);
	}
}

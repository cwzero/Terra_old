package com.liquidforte.terra.inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import com.liquidforte.terra.config.AppOptions;

public class InjectorBuilder {
	public static Injector buildApp(AppOptions options) {
		switch (options.getMode()) {
		default:
		case "default":
			return build(new DefaultAppModule(options));
		}
	}

	public static Injector build(Module... m) {
		List<Module> modules = new ArrayList<Module>(Arrays.asList(m));
		Module[] defaultModules = { new JacksonModule(), new JerseyModule(), new TwitchClientModule(),
				new TerraModule(), new DatabaseModule(), new CommandModule(), new CacheModule() };
		modules.addAll(Arrays.asList(defaultModules));
		return Guice.createInjector(modules);
	}
}

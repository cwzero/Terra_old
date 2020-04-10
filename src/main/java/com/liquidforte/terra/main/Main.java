package com.liquidforte.terra.main;

import com.google.inject.Injector;
import com.liquidforte.terra.app.App;
import com.liquidforte.terra.config.AppOptions;
import com.liquidforte.terra.inject.InjectorBuilder;

public class Main {
	public static void main(String[] args) {
		Injector injector = InjectorBuilder.buildApp(new AppOptions(args));
		App app = injector.getInstance(App.class);
		app.run();
	}
}

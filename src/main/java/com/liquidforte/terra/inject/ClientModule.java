package com.liquidforte.terra.inject;

import com.google.inject.AbstractModule;
import com.liquidforte.terra.client.TerraClient;

public class ClientModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(TerraClient.class);
	}
}

package com.liquidforte.terra.inject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.RequestEntityProcessing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

public class JerseyModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ApacheConnectorProvider.class);
		bind(new TypeLiteral<ContextResolver<ObjectMapper>>() {
		}).to(JacksonContextResolver.class);
	}

	@Provides
	protected ClientConfig getClientConfig(ApacheConnectorProvider acProvider,
			ContextResolver<ObjectMapper> jacksonResolver) {
		return new ClientConfig()
				//.connectorProvider(acProvider)
				.register(jacksonResolver)
			    .property(ClientProperties.REQUEST_ENTITY_PROCESSING, RequestEntityProcessing.BUFFERED);
	}

	@Provides
	protected ClientBuilder getClientBuilder(ClientConfig clientConfig, ApacheConnectorProvider acProvider) {
		return ClientBuilder.newBuilder().withConfig(clientConfig);
	}

	@Provides
	protected Client getClient(ClientBuilder clientBuilder) {
		return clientBuilder.build();
	}
}

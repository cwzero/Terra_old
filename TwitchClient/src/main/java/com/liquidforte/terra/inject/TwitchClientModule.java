package com.liquidforte.terra.inject;

import javax.ws.rs.client.ClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.liquidforte.terra.twitch.Twitch;

import feign.Feign;
import feign.Feign.Builder;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs2.JAXRSClient;

public class TwitchClientModule extends AbstractModule {
	private final String TWITCH_URL = "https://addons-ecs.forgesvc.net/api/v2";

	@Override
	protected void configure() {
		bind(Builder.class).toProvider(Feign::builder);
	}

	@Provides
	protected Decoder getDecoder(ObjectMapper mapper) {
		return new JacksonDecoder(mapper);
	}

	@Provides
	protected Encoder getEncoder(ObjectMapper mapper) {
		return new JacksonEncoder(mapper);
	}

	@Provides
	protected Twitch getTwitch(ClientBuilder clientBuilder, Builder builder, Decoder decoder, Encoder encoder) {
		return builder.client(new JAXRSClient(clientBuilder)).decoder(decoder).encoder(encoder).target(Twitch.class,
				TWITCH_URL);
	}
}

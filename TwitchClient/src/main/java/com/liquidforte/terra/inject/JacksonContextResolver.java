package com.liquidforte.terra.inject;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

public class JacksonContextResolver implements ContextResolver<ObjectMapper> {
	private ObjectMapper mapper;
	
	@Inject
	public JacksonContextResolver(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}
}

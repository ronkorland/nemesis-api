package com.nemesis.api.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

@Provider
public class MessageBodyWriterJSON extends JacksonJsonProvider {
	public MessageBodyWriterJSON() {
	}

	@Override
	public ObjectMapper locateMapper(Class<?> type, MediaType mediaType) {
		ObjectMapper mapper = super.locateMapper(type, mediaType);
		// DateTime in ISO format "2012-04-07T17:00:00.000+0000" instead of
		// 'long' format
		mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,
				false);
		mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		return mapper;
	}
}

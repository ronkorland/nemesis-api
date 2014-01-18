package com.nemesis.api.type;

public class JodaDateTimeSerializer extends AbstractDateSerializer {

	@Override
	protected String getPattern() {
		return JodaDateTimeDeserializer.FORMAT; // use same format for serialize
												// and deserialize
	}

}

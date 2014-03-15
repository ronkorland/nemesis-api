package com.nemesis.api.type;

public class JodaDateSerializer extends AbstractDateSerializer {

	public static final String FORMAT = "dd-MM-yyyy";

	@Override
	protected String getPattern() {
		return FORMAT;
	}

}

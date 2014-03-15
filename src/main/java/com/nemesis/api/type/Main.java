package com.nemesis.api.type;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormat
				.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		String text = "2014-03-16T22:00:00.000Z";
		DateTime date = formatter.parseDateTime(text);
	}

}

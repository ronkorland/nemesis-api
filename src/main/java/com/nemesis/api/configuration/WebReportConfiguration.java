package com.nemesis.api.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WebReportConfiguration {

	public String getFilePath() {
		String path = getClass().getResource("").getFile();
		path = path.substring(0, (path.indexOf("WEB-INF")));
		path = path + "files/";
		return path;
	}
}

package com.nemesis.api.model.selenium;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.nemesis.api.model.BaseModel;

@Document(collection = "nodes")
public class Node extends BaseModel {

	private String nodeHost;

	private List<Browser> browsers;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastSync;

	public String getNodeHost() {
		return nodeHost;
	}

	public void setNodeHost(String nodeHost) {
		this.nodeHost = nodeHost;
	}

	public List<Browser> getBrowsers() {
		return browsers;
	}

	public void setBrowsers(List<Browser> browsers) {
		this.browsers = browsers;
	}

	public LocalDateTime getLastSync() {
		return lastSync;
	}

	public void setLastSync(LocalDateTime lastSync) {
		this.lastSync = lastSync;
	}

}

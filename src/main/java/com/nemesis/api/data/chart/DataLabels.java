package com.nemesis.api.data.chart;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataLabels {

	private boolean enabled;
	private String connectorColor = "#000000";
	private String format = "";

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getConnectorColor() {
		return connectorColor;
	}

	public void setConnectorColor(String connectorColor) {
		this.connectorColor = connectorColor;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}

package com.nemesis.api.data.chart;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tooltip {

	private String pointFormat;

	private String valueSuffix;

	private boolean crosshairs = true;

	private boolean shared = true;

	public boolean isCrosshairs() {
		return crosshairs;
	}

	public void setCrosshairs(boolean crosshairs) {
		this.crosshairs = crosshairs;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public String getPointFormat() {
		return pointFormat;
	}

	public void setPointFormat(String pointFormat) {
		this.pointFormat = pointFormat;
	}

	public String getValueSuffix() {
		return valueSuffix;
	}

	public void setValueSuffix(String valueSuffix) {
		this.valueSuffix = valueSuffix;
	}

}

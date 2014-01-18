package com.nemesis.api.data.chart;

public class Line {

	private DataLabels dataLabels;
	private boolean enableMouseTracking;

	public DataLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	public boolean isEnableMouseTracking() {
		return enableMouseTracking;
	}

	public void setEnableMouseTracking(boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
	}

}

package com.nemesis.api.data.chart;

public class LineData {

	private int y;

	private String url;

	public LineData(int y, String url) {
		this.y = y;
		this.url = url;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

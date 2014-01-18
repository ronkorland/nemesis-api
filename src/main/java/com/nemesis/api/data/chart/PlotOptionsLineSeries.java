package com.nemesis.api.data.chart;

public class PlotOptionsLineSeries {
	
	private String cursor = "pointer";

	private Point point;

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}

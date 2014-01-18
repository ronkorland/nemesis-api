package com.nemesis.api.data.chart;

public class Point {

	private Events events;

	public Point(Events events) {
		this.events = events;
	}

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}
}

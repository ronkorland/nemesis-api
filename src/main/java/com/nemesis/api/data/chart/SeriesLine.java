package com.nemesis.api.data.chart;

import java.util.List;

public class SeriesLine {

	private String name;
	private List<LineData> data;
	private String color = null;
	private int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LineData> getData() {
		return data;
	}

	public void setData(List<LineData> data) {
		this.data = data;
	}


}

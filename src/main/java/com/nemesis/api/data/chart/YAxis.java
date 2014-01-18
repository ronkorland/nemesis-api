package com.nemesis.api.data.chart;

import java.util.List;

public class YAxis {

	private Title title;

	private List<PlotLines> plotLines;

	public List<PlotLines> getPlotLines() {
		return plotLines;
	}

	public void setPlotLines(List<PlotLines> plotLines) {
		this.plotLines = plotLines;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
}

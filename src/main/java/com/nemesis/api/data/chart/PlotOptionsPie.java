package com.nemesis.api.data.chart;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlotOptionsPie {

	private Pie pie;

	public Pie getPie() {
		return pie;
	}

	public void setPie(Pie pie) {
		this.pie = pie;
	}
}

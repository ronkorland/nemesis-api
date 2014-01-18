package com.nemesis.api.data.chart;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pie {

	private boolean allowPointSelect = true;
	private String cursor = "pointer";
	private boolean showInLegend;
	private DataLabels dataLabels;

	public boolean isAllowPointSelect() {
		return allowPointSelect;
	}

	public void setAllowPointSelect(boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public DataLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	public boolean isShowInLegend() {
		return showInLegend;
	}

	public void setShowInLegend(boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

}

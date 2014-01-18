package com.nemesis.api.data.chart;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SeriesPie {

	private String type = "pie";
	private String name;
	private List<DataPie> data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DataPie> getData() {
		return data;
	}

	public void setData(List<DataPie> data) {
		this.data = data;
	}
}

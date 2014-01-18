package com.nemesis.api.data.chart;

public class DataLine {

	private int y;
	private String url = "";
	
	public DataLine(int y, String url){
		this.y = y;
		this.url = url;
	}
	
	public DataLine(int y){
		this(y, "");
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

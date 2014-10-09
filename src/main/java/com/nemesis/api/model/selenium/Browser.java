package com.nemesis.api.model.selenium;

public class Browser {

	private String name;

	private String version;

	private String driverVersion;

	private int numOfRunning = 0;

	public Browser() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getNumOfRunning() {
		return numOfRunning;
	}

	public void setNumOfRunning(int numOfRunning) {
		this.numOfRunning = numOfRunning;
	}

	public String getDriverVersion() {
		return driverVersion;
	}

	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

}

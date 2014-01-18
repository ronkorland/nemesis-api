package com.nemesis.api.model;

import java.util.NoSuchElementException;

import org.joda.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.nemesis.api.constants.Status;
import com.nemesis.api.data.SuiteData;

@Document(collection = "suites")
public class Suite extends BaseModel {

	private String suiteName;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;

	private int numberOfFails;

	private int numberOfSkips;

	private int numberOfTests;

	private Status suiteStatus;

	public Suite() {
		super();
	}

	public Suite(SuiteData data) {
		super(data);
		setEndTime(data.getEndTime());
		setSuiteName(data.getSuiteName());
		setStartTime(data.getStartTime());
	}

	public Suite merge(Suite other) {
		if (!this.getId().equals(other.getId())) {
			throw new NoSuchElementException("Suite object doesn't match");
		}
		if (this.getEndTime() == null
				|| this.getEndTime().isBefore(other.getEndTime())) {
			this.setEndTime(other.getEndTime());
		}
		if (this.getNumberOfTests() < other.getNumberOfTests()) {
			this.setNumberOfTests(other.getNumberOfTests());
		}
		if (this.getNumberOfSkips() < other.getNumberOfSkips()) {
			this.setNumberOfSkips(other.getNumberOfSkips());
		}
		if (this.getNumberOfFails() < other.getNumberOfFails()) {
			this.setNumberOfFails(other.getNumberOfFails());
		}
		if (this.getSuiteStatus() != other.getSuiteStatus()) {
			this.setSuiteStatus(other.getSuiteStatus());
		}
		return this;
	}

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public int getNumberOfFails() {
		return numberOfFails;
	}

	public void setNumberOfFails(int numberOfFails) {
		this.numberOfFails = numberOfFails;
	}

	public int getNumberOfSkips() {
		return numberOfSkips;
	}

	public void setNumberOfSkips(int numberOfSkips) {
		this.numberOfSkips = numberOfSkips;
	}

	public int getNumberOfTests() {
		return numberOfTests;
	}

	public void setNumberOfTests(int numberOfTests) {
		this.numberOfTests = numberOfTests;
	}

	public Status getSuiteStatus() {
		return suiteStatus;
	}

	public void setSuiteStatus(Status suiteStatus) {
		this.suiteStatus = suiteStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((suiteName == null) ? 0 : suiteName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Suite other = (Suite) obj;
		if (suiteName.equalsIgnoreCase(other.getSuiteName())) {
			if (endTime.isBefore(other.getEndTime())) {
				return true;
			}
		}
		return false;
	}

}

package com.nemesis.api.data.suite;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import com.nemesis.api.constants.Status;
import com.nemesis.api.data.BaseData;
import com.nemesis.api.model.Suite;
import com.nemesis.api.type.JodaDateTimeDeserializer;
import com.nemesis.api.type.JodaDateTimeSerializer;

public class SuiteData extends BaseData {

	private String suiteName;

	@JsonSerialize(using = JodaDateTimeSerializer.class)
	@JsonDeserialize(using = JodaDateTimeDeserializer.class)
	private LocalDateTime startTime;

	@JsonSerialize(using = JodaDateTimeSerializer.class)
	@JsonDeserialize(using = JodaDateTimeDeserializer.class)
	private LocalDateTime endTime;

	private String runningTime;

	private Status suiteStatus;

	private int numberOfTests = 0;

	private int numberOfFails = 0;

	private int numberOfSkips = 0;

	private String htmlStatus = "";

	public SuiteData() {
		super();
	}

	public SuiteData(Suite suite) {
		super(suite);
		setNumberOfFails(suite.getNumberOfFails());
		setNumberOfSkips(suite.getNumberOfSkips());
		setNumberOfTests(suite.getNumberOfTests());
		setEndTime(suite.getEndTime());
		setSuiteName(suite.getSuiteName());
		setStartTime(suite.getStartTime());
		setSuiteStatus(suite.getSuiteStatus());
		clacRunningTime();
		calcStatus();
	}
	
	private void calcStatus(){
		if (getNumberOfFails() > 0 || getNumberOfSkips() > 0) {
			setHtmlStatus("<span class='label-status-FAILURE'>Failed: "
					+ getNumberOfFails()
					+ "</span> | <span class='label-status-SKIP'>Skipped: "
					+ getNumberOfSkips() + "</span>");
			setSuiteStatus(Status.FAILURE);
		} else {
			setSuiteStatus(Status.SUCCESS);
			setHtmlStatus("<span class='label-status-SUCCESS'>"
					+ Status.SUCCESS + "</span>");
		}
	}

	private void clacRunningTime() {
		if (getEndTime() != null && getStartTime() != null) {
			Duration dur = new Duration(getStartTime().toDateTime(),
					getEndTime().toDateTime());
			Period p = dur.toPeriod();
			setRunningTime(p.getHours() + ":" + p.getMinutes() + ":"
					+ p.getSeconds());
		}
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

	public int getNumberOfTests() {
		return numberOfTests;
	}

	public void setNumberOfTests(int numberOfTests) {
		this.numberOfTests = numberOfTests;
	}

	public int getNumberOfFails() {
		return numberOfFails;
	}

	public void setNumberOfFails(int numberOfFails) {
		this.numberOfFails = numberOfFails;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	public int getNumberOfSkips() {
		return numberOfSkips;
	}

	public void setNumberOfSkips(int numberOfSkips) {
		this.numberOfSkips = numberOfSkips;
	}

	public String getHtmlStatus() {
		return htmlStatus;
	}

	public void setHtmlStatus(String htmlStatus) {
		this.htmlStatus = htmlStatus;
	}

	public Status getSuiteStatus() {
		return suiteStatus;
	}

	public void setSuiteStatus(Status suiteStatus) {
		this.suiteStatus = suiteStatus;
	}

}

package com.nemesis.api.data.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import com.nemesis.api.constants.Status;
import com.nemesis.api.data.BaseData;
import com.nemesis.api.model.Test;
import com.nemesis.api.model.TestParameter;
import com.nemesis.api.type.JodaDateTimeDeserializer;
import com.nemesis.api.type.JodaDateTimeSerializer;

@XmlRootElement
public class TestData extends BaseData {

	private String suiteId;

	private String testName;

	private Status testStatus;

	@JsonSerialize(using = JodaDateTimeSerializer.class)
	@JsonDeserialize(using = JodaDateTimeDeserializer.class)
	private LocalDateTime startTime;

	@JsonSerialize(using = JodaDateTimeSerializer.class)
	@JsonDeserialize(using = JodaDateTimeDeserializer.class)
	private LocalDateTime endTime;

	private String runningTime;

	private long durationSec;

	private List<TestParameterData> parameters;

	private List<String> testAttachments;

	private List<String> logs;

	private FailureReasonData failureReason;

	private String className;

	private String method;

	private List<String> testGroups;

	private String env;

	public TestData() {
		super();
	}

	public TestData(Test test) {
		super(test);
		setSuiteId(test.getSuiteId());
		setEndTime(test.getEndTime());
		setStartTime(test.getStartTime());
		setTestName(test.getTestName());
		setTestStatus(test.getTestStatus());
		setTestAttachments(test.getTestAttachments());
		setParameters(convertTestParameters(test.getParameters()));
		setClassName(test.getClassName());
		setMethod(test.getMethod());
		setTestGroups(test.getTestGroups());
		setLogs(test.getLogs());
		if (test.getFailureReason() != null) {
			FailureReasonData failureReasonModel = new FailureReasonData(test.getFailureReason());
			setFailureReason(failureReasonModel);
		}
		clacRunningTime();
		setEnv(getEnvParam(test));
	}

	private String getEnvParam(Test test) {
		String env = "";
		List<TestParameter> parameters = test.getParameters();
		if (parameters != null && parameters.size() > 0) {
			for (TestParameter p : parameters) {
				if (p.getParamName().equalsIgnoreCase("env")) {
					env = p.getParamValue();
					break;
				}
			}
		}

		return env;
	}

	private List<TestParameterData> convertTestParameters(List<TestParameter> parameters) {
		List<TestParameterData> datas = new ArrayList<TestParameterData>();

		if (parameters != null && parameters.size() > 0) {
			for (TestParameter parameter : parameters) {
				TestParameterData data = new TestParameterData(parameter);
				datas.add(data);
			}
		}
		return datas;
	}

	private void clacRunningTime() {
		if (getEndTime() != null && getStartTime() != null) {
			Duration dur = new Duration(getStartTime().toDateTime(), getEndTime().toDateTime());
			setDurationSec(dur.getStandardSeconds());
			Period p = dur.toPeriod();
			setRunningTime(p.getHours() + ":" + p.getMinutes() + ":" + p.getSeconds());
		}
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Status getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(Status testStatus) {
		this.testStatus = testStatus;
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
		if (endTime != null) {
			this.endTime = endTime;
		}
	}

	public List<String> getTestAttachments() {
		return testAttachments;
	}

	public void setTestAttachments(List<String> testAttachments) {
		this.testAttachments = testAttachments;
	}

	public FailureReasonData getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(FailureReasonData failureReason) {
		this.failureReason = failureReason;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<TestParameterData> getParameters() {
		return parameters;
	}

	public void setParameters(List<TestParameterData> parameters) {
		this.parameters = parameters;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<String> getTestGroups() {
		return testGroups;
	}

	public void setTestGroups(List<String> testGroups) {
		this.testGroups = testGroups;
	}

	public String getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(String suiteId) {
		this.suiteId = suiteId;
	}

	public long getDurationSec() {
		return durationSec;
	}

	public void setDurationSec(long durationSec) {
		this.durationSec = durationSec;
	}

	public List<String> getLogs() {
		return logs;
	}

	public void setLogs(List<String> logs) {
		this.logs = logs;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

}

package com.nemesis.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.joda.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nemesis.api.constants.Status;
import com.nemesis.api.data.test.TestData;
import com.nemesis.api.data.test.TestParameterData;

@Document(collection = "tests")
public class Test extends BaseModel {

	private String suiteId;

	private String testName;

	private Status testStatus;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private List<TestParameter> parameters;

	private String className;

	private String method;

	private List<String> testAttachments;

	private FailureReason failureReason;

	private List<String> testGroups;

	public Test() {
		super();
	}

	public Test(TestData data) {
		super(data);
		setSuiteId(data.getSuiteId());
		setEndTime(data.getEndTime());
		setStartTime(data.getStartTime());
		setTestName(data.getTestName());
		setTestStatus(data.getTestStatus());
		setTestAttachments(data.getTestAttachments());
		setClassName(data.getClassName());
		setMethod(data.getMethod());
		setTestGroups(data.getTestGroups());
		setParameters(convertTestParameters(data.getParameters()));
		if (data.getFailureReason() != null) {
			FailureReason failureReasonData = new FailureReason(
					data.getFailureReason());
			setFailureReason(failureReasonData);
		}

	}

	private List<TestParameter> convertTestParameters(
			List<TestParameterData> datas) {
		List<TestParameter> parameters = new ArrayList<TestParameter>();
		if (datas != null && datas.size() > 0) {
			for (TestParameterData data : datas) {
				TestParameter parameter = new TestParameter(data);
				parameters.add(parameter);
			}
		}
		return parameters;
	}

	public Test(String testName, Status testStatus) {
		this();
		this.testName = testName;
		this.testStatus = testStatus;
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
		this.endTime = endTime;
	}

	public List<String> getTestAttachments() {
		return testAttachments;
	}

	public void setTestAttachments(List<String> testAttachments) {
		this.testAttachments = testAttachments;
	}

	public Test merge(Test other) {
		if (!this.getId().equals(other.getId())) {
			throw new NoSuchElementException("Test object doesn't match");
		}
		if (this.getEndTime() == null) {
			this.setEndTime(other.getEndTime());
		}
		if (this.getTestStatus() == null
				|| this.getTestStatus() != other.getTestStatus()) {
			this.setTestStatus(other.getTestStatus());
		}
		if (this.getTestAttachments() == null
				|| !isTheSame(
						this.getTestAttachments().toArray(
								new String[this.getTestAttachments().size()]),
						other.testAttachments
								.toArray(new String[other.testAttachments
										.size()]))) {
			this.setTestAttachments(other.getTestAttachments());
		}
		return this;
	}

	private boolean isTheSame(String[] arr1, String[] arr2) {
		if (arr1.length != arr2.length)
			return false;
		for (int i = 0; i < arr1.length; i++)
			if (!arr1[i].equals(arr2[i]))
				return false;
		return true;
	}

	public FailureReason getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(FailureReason failureReason) {
		this.failureReason = failureReason;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<TestParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<TestParameter> parameters) {
		this.parameters = parameters;
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
}

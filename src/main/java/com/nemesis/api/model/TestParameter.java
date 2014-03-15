package com.nemesis.api.model;

import com.nemesis.api.data.test.TestParameterData;

public class TestParameter {

	private String paramName;

	private String paramValue;

	private String paramSource;
	
	public TestParameter(){
		super();
	}

	public TestParameter(TestParameterData data) {
		setParamName(data.getParamName());
		setParamValue(data.getParamValue());
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamSource() {
		return paramSource;
	}

	public void setParamSource(String paramSource) {
		this.paramSource = paramSource;
	}
}

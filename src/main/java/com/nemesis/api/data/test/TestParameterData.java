package com.nemesis.api.data.test;

import com.nemesis.api.model.TestParameter;

public class TestParameterData {

	private String paramName;

	private String paramValue;

	private String paramSource;

	public TestParameterData() {
		super();
	}

	public TestParameterData(TestParameter param) {
		setParamName(param.getParamName());
		setParamValue(param.getParamValue());
		setParamSource(param.getParamSource());
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

	public String getParamName() {
		return paramName;
	}
}

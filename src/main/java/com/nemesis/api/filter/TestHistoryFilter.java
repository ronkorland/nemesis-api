package com.nemesis.api.filter;

public class TestHistoryFilter {

	private String className;

	private String method;

	public TestHistoryFilter(String className, String method) {
		super();
		this.className = className;
		this.method = method;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public boolean isEmpty() {
		if (this.className == null || this.method == null) {
			return true;
		}
		return false;
	}

}

package com.nemesis.api.data;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class TestHistoryListData {

	@JsonDeserialize(as = ArrayList.class, contentAs = TestHistoryData.class)
	private List<TestHistoryData> tests;

	private long total;

	public List<TestHistoryData> getTests() {
		return tests;
	}

	public void setTests(List<TestHistoryData> tests) {
		this.tests = tests;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}

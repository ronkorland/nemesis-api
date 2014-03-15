package com.nemesis.api.data.test;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class TestsData {

	@JsonDeserialize(as = ArrayList.class, contentAs = TestData.class)
	private List<TestData> tests;

	private long total;

	private long totalPages;

	public long getTotal() {
		return total;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<TestData> getTests() {
		return tests;
	}

	public void setTests(List<TestData> tests) {
		this.tests = tests;
	}

}

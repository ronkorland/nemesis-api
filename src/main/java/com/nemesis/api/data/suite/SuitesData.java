package com.nemesis.api.data.suite;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class SuitesData {

	@JsonDeserialize(as = ArrayList.class, contentAs = SuiteData.class)
	private List<SuiteData> suites;

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

	public List<SuiteData> getSuites() {
		return suites;
	}

	public void setSuites(List<SuiteData> suites) {
		this.suites = suites;
	}
}

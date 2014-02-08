package com.nemesis.api.data;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class TestPlansData {

	@JsonDeserialize(as = ArrayList.class, contentAs = TestPlanData.class)
	private List<TestPlanData> testPlans;

	private long total;

	private long totalPages;

	public List<TestPlanData> getTestPlans() {
		return testPlans;
	}

	public void setTestPlans(List<TestPlanData> testPlans) {
		this.testPlans = testPlans;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

}

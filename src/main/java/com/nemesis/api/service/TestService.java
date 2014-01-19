package com.nemesis.api.service;

import java.util.List;

import com.nemesis.api.data.SummaryData;
import com.nemesis.api.data.TestData;
import com.nemesis.api.data.TestHistoryListData;
import com.nemesis.api.data.TestMethodData;
import com.nemesis.api.data.TestsData;
import com.nemesis.api.filter.TestFilter;

public interface TestService {

	public TestData create(TestData testData);

	public TestData delete(TestData testData);

	public TestData update(TestData testData);

	public TestData findById(String testId);

	public TestsData findTests(TestFilter filter);

	public List<TestMethodData> getMethodsBySuiteId(String suiteId);
	
	public List<TestMethodData> getMethods();

	public SummaryData findLast24HoursSummary();
	
	public TestHistoryListData getTestHistory(String testId);
}

package com.nemesis.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nemesis.api.constants.Status;
import com.nemesis.api.data.SummaryData;
import com.nemesis.api.data.TestData;
import com.nemesis.api.data.TestHistoryData;
import com.nemesis.api.data.TestHistoryListData;
import com.nemesis.api.data.TestMethodData;
import com.nemesis.api.data.TestsData;
import com.nemesis.api.filter.TestFilter;
import com.nemesis.api.filter.TestHistoryFilter;
import com.nemesis.api.model.Suite;
import com.nemesis.api.model.Test;
import com.nemesis.api.repository.SuiteRepository;
import com.nemesis.api.repository.TestRepository;
import com.nemesis.api.service.TestService;

@Service("testService")
@Scope("singleton")
public class TestServiceImpl implements TestService {

	@Autowired
	TestRepository testRepository;

	@Autowired
	SuiteRepository suiteRepository;

	@Override
	public TestData create(TestData testData) {
		try {
			Test test = new Test(testData);
			Test returnTest = testRepository.create(test);
			TestData returnTestData = new TestData(returnTest);
			if (StringUtils.isNotBlank(test.getSuiteId())) {
				Suite suite = suiteRepository.findById(test.getSuiteId());
				if (suite.getEndTime() == null) {
					suite.setEndTime(test.getEndTime());
				}
				int numOfTest = suite.getNumberOfTests() + 1;
				suite.setNumberOfTests(numOfTest);
				if (test.getTestStatus() == Status.SUCCESS) {
					if (suite.getSuiteStatus() == null) {
						suite.setSuiteStatus(Status.SUCCESS);
					}
				} else if (test.getTestStatus() == Status.FAILURE) {
					int numOfFailure = suite.getNumberOfFails() + 1;
					suite.setNumberOfFails(numOfFailure);
					if (suite.getSuiteStatus() != Status.FAILURE) {
						suite.setSuiteStatus(Status.FAILURE);
					}
				} else if (test.getTestStatus() == Status.SKIP) {
					int numOfSkip = suite.getNumberOfSkips() + 1;
					suite.setNumberOfSkips(numOfSkip);
					if (suite.getSuiteStatus() == Status.SUCCESS
							|| suite.getSuiteStatus() == null) {
						suite.setSuiteStatus(Status.SKIP);
					}
				}
				suiteRepository.update(suite);
			}

			return returnTestData;
		} catch (Throwable t) {
			return null;
		}
	}

	@Override
	public TestData delete(TestData testData) {
		Test test = new Test(testData);
		Test returnTest = testRepository.delete(test);
		TestData returnTestData = new TestData(returnTest);
		return returnTestData;
	}

	@Override
	public TestData update(TestData testData) {
		Test test = new Test(testData);
		Test returnTest = testRepository.update(test);
		TestData returnTestData = new TestData(returnTest);
		return returnTestData;
	}

	@Override
	public TestData findById(String testId) {
		Test test = testRepository.findById(testId);
		TestData data = new TestData(test);
		return data;
	}

	@Override
	public TestsData findTests(TestFilter filter) {
		List<Test> tests = testRepository.findTests(filter);
		long count = testRepository.count(filter);
		TestsData testsData = new TestsData();
		testsData.setTotal(count);
		long totalPages = (long) Math.ceil((double) count
				/ (double) filter.getPageSize());
		testsData.setTotalPages(totalPages);

		if (tests != null && tests.size() > 0) {

			List<TestData> lstTests = new ArrayList<TestData>();
			for (Test test : tests) {
				lstTests.add(new TestData(test));
			}
			testsData.setTests(lstTests);
			return testsData;
		} else {
			return testsData;
		}
	}

	@Override
	public List<TestMethodData> getMethodsBySuiteId(String suiteId) {
		List<String> methos = testRepository.getMethodsBySuiteId(suiteId);
		List<TestMethodData> datas = new ArrayList<TestMethodData>();
		if (methos != null && methos.size() > 0) {
			for (String method : methos) {
				datas.add(new TestMethodData(method));
			}
		}
		return datas;
	}

	@Override
	public SummaryData findLast24HoursSummary() {
		List<Test> tests = testRepository.findLast24Hours();
		SummaryData summaryData = new SummaryData();
		int amountOfFailed = 0;

		if (tests != null && tests.size() > 0) {
			for (Test test : tests) {
				if (test.getTestStatus() == Status.FAILURE) {
					amountOfFailed++;
				}
			}
			summaryData.setTotal(tests.size());
			summaryData.setAmountOfFailed(amountOfFailed);
			summaryData.setAmountOfSuccess(tests.size() - amountOfFailed);
		}

		return summaryData;
	}

	@Override
	public List<TestMethodData> getMethods() {
		List<String> methos = testRepository.getMethods();
		List<TestMethodData> datas = new ArrayList<TestMethodData>();
		if (methos != null && methos.size() > 0) {
			for (String method : methos) {
				datas.add(new TestMethodData(method));
			}
		}
		return datas;
	}

	@Override
	public TestHistoryListData getTestHistory(String testId) {
		Test testMain = null;
		testMain = testRepository.findById(testId);
		if (testMain == null) {
			return null;
		}
		String className = testMain.getClassName();
		String methodName = testMain.getMethod();
		TestHistoryFilter filter = new TestHistoryFilter(className, methodName);
		List<Test> testList = testRepository.getTestHistory(filter);

		if (testList != null && testList.size() > 0) {
			List<TestHistoryData> testHistoryList = new ArrayList<TestHistoryData>();
			for (Test test : testList) {
				TestHistoryData historyData = new TestHistoryData(test);
				if (test.getId().equals(testId)) {
					historyData.setMe(true);
				}
				testHistoryList.add(historyData);
			}

			TestHistoryListData historyListData = new TestHistoryListData();
			historyListData.setTests(testHistoryList);
			historyListData.setTotal(testHistoryList.size());
			return historyListData;
		}
		return null;
	}
}

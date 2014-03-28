package com.nemesis.api.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nemesis.api.comparator.SuiteByEndTime;
import com.nemesis.api.constants.Status;
import com.nemesis.api.data.chart.LastActivity;
import com.nemesis.api.data.chart.LineChart;
import com.nemesis.api.data.chart.PieChart;
import com.nemesis.api.data.suite.SuiteData;
import com.nemesis.api.data.suite.SuiteNameData;
import com.nemesis.api.data.suite.SuitesData;
import com.nemesis.api.data.summary.SummaryData;
import com.nemesis.api.filter.SuiteFilter;
import com.nemesis.api.model.Suite;
import com.nemesis.api.repository.SuiteRepository;
import com.nemesis.api.service.SuiteService;

@Service("suiteService")
@Scope("singleton")
public class SuiteServiceImpl implements SuiteService {

	@Autowired
	private SuiteRepository suiteRepository;

	@Override
	public SuitesData findAllSuites(SuiteFilter filter) {
		List<Suite> suites = suiteRepository.findAllSuites(filter);
		SuitesData suitesData = new SuitesData();

		long count = suiteRepository.count(filter);
		suitesData.setTotal(count);
		long totalPages = 1;
		if (filter.getPageSize() != 0) {
			totalPages = (long) Math.ceil((double) count
					/ (double) filter.getPageSize());
		}

		suitesData.setTotalPages(totalPages);

		if (suites != null && suites.size() > 0) {
			List<SuiteData> suiteDataList = new ArrayList<SuiteData>();
			for (Suite suite : suites) {
				SuiteData data = new SuiteData(suite);
				suiteDataList.add(data);
			}
			suitesData.setSuites(suiteDataList);
			return suitesData;
		} else {
			return suitesData;
		}
	}

	@Override
	public SuiteData findById(String suiteId) {
		Suite suite = suiteRepository.findById(suiteId);
		if (suite != null) {
			SuiteData data = new SuiteData(suite);
			return data;
		} else {
			return null;
		}
	}

	@Override
	public SuitesData findLast24HoursDistinct() {
		SuitesData suitesData = new SuitesData();
		List<Suite> suites = suiteRepository.findLast24Hours();
		if (suites != null && suites.size() > 0) {
			List<SuiteData> suiteDataList = new ArrayList<SuiteData>();
			Collections.sort(suites, new SuiteByEndTime());
			Set<Suite> suitesSet = new HashSet<Suite>(suites);
			for (Suite suite : suitesSet) {
				SuiteData data = new SuiteData(suite);
				suiteDataList.add(data);
			}
			suitesData.setSuites(suiteDataList);
			suitesData.setTotal(suiteDataList.size());
		}
		return suitesData;
	}

	@Override
	public SuitesData findLast24Hours() {
		SuitesData suitesData = new SuitesData();
		List<Suite> suites = suiteRepository.findLast24Hours();
		if (suites != null && suites.size() > 0) {
			List<SuiteData> suiteDataList = new ArrayList<SuiteData>();
			for (Suite suite : suites) {
				SuiteData data = new SuiteData(suite);
				suiteDataList.add(data);
			}
			suitesData.setSuites(suiteDataList);
			suitesData.setTotal(suiteRepository.countLast24Hours());
		}
		return suitesData;
	}

	@Override
	public SuitesData findSuiteBefore(int days) {
		List<Suite> suites = suiteRepository.findSuiteBefore(days);
		if (suites.size() > 0) {
			Collections.sort(suites, new SuiteByEndTime());
			Set<Suite> suitesSet = new HashSet<Suite>(suites);
			List<SuiteData> suiteDataList = new ArrayList<SuiteData>();
			for (Suite suite : suitesSet) {
				SuiteData data = new SuiteData(suite);
				suiteDataList.add(data);
			}
			SuitesData suitesData = new SuitesData();
			suitesData.setSuites(suiteDataList);
			return suitesData;
		} else {
			return null;
		}
	}

	private HashMap<LocalDate, LastActivity> calcAmountOfTestsPerDay() {
		int amountOfDays = 30;
		HashMap<LocalDate, LastActivity> map = new HashMap<LocalDate, LastActivity>();
		for (int i = 0; i < amountOfDays; i++) {
			LocalDate date = new LocalDate();
			date = date.minusDays(i);
			SuitesData suites = findSuiteBefore(i);
			LastActivity activity = new LastActivity();
			if (suites != null && suites.getSuites() != null
					&& suites.getSuites().size() > 0) {
				for (SuiteData suite : suites.getSuites()) {
					activity.addToAmountOfFailed(suite.getNumberOfFails());
					activity.addToAmountOfSuccess(suite.getNumberOfTests()
							- (suite.getNumberOfFails() + suite
									.getNumberOfSkips()));
					activity.addToAmountOfTests(suite.getNumberOfTests());
				}

			}
			map.put(date, activity);
		}
		return map;
	}

	@Override
	public LineChart getAmountOfTestChart() {
		return LineChart.convertToLineChart(calcAmountOfTestsPerDay());
	}

	private LastActivity calcSuitesLastActivity() {
		LastActivity activity = new LastActivity();
		int successCount = 0;
		int failedCount = 0;
		SuitesData suites = findLast24HoursDistinct();
		if (suites != null && suites.getSuites().size() > 0) {
			for (SuiteData suite : suites.getSuites()) {
				switch (suite.getSuiteStatus()) {
				case SUCCESS:
					successCount++;
					break;
				case SKIP:
				case FAILURE:
					failedCount++;
					break;
				}
			}
		}
		activity.setAmountOfFailed(failedCount);
		activity.setAmountOfSuccess(successCount);
		return activity;
	}

	private LastActivity calcTestsLastActivity() {
		LastActivity activity = new LastActivity();
		int successCount = 0;
		int failedCount = 0;
		SuitesData suites = findLast24HoursDistinct();
		if (suites != null && suites.getSuites().size() > 0) {
			for (SuiteData suite : suites.getSuites()) {
				failedCount = failedCount + suite.getNumberOfFails();
				successCount = successCount
						+ (suite.getNumberOfTests() - (suite.getNumberOfFails() + suite
								.getNumberOfSkips()));
			}
		}
		activity.setAmountOfFailed(failedCount);
		activity.setAmountOfSuccess(successCount);
		return activity;

	}

	@Override
	public PieChart getLast24HoursTests() {
		return PieChart.convertToPieChart(calcTestsLastActivity(),
				"Tests Activity", "Tests on the last 24 hours");
	}

	@Override
	public PieChart getLast24HoursSuites() {
		return PieChart.convertToPieChart(calcSuitesLastActivity(),
				"Suites Activity", "Suites on the last 24 hours");
	}

	@Override
	public SuiteData create(SuiteData suiteData) {
		try {
			Suite suite = new Suite(suiteData);
			Suite returnSuite = suiteRepository.create(suite);
			SuiteData returnSuiteData = new SuiteData(returnSuite);
			return returnSuiteData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public SuiteData update(SuiteData suiteData) {
		Suite currentSuite = suiteRepository.findById(suiteData.getId());
		Suite suite = new Suite(suiteData);
		currentSuite = currentSuite.merge(suite);

		Suite returnSuite = suiteRepository.update(currentSuite);
		SuiteData returnSuiteData = new SuiteData(returnSuite);
		return returnSuiteData;
	}

	@Override
	public SuiteData delete(SuiteData suiteData) {
		Suite suite = new Suite(suiteData);
		Suite returnSuite = suiteRepository.delete(suite);
		SuiteData returnSuiteData = new SuiteData(returnSuite);
		return returnSuiteData;
	}

	@Override
	public List<SuiteNameData> getSuiteNames() {
		List<SuiteNameData> names = new ArrayList<SuiteNameData>();
		List<String> namesString = suiteRepository.getSuiteNames();
		for (String string : namesString) {
			names.add(new SuiteNameData(string));
		}
		return names;
	}

	@Override
	public SummaryData findLast24HoursSummary() {
		List<Suite> suites = suiteRepository.findLast24Hours();
		SummaryData summaryData = new SummaryData();
		int amountOfFailed = 0;

		if (suites != null && suites.size() > 0) {
			for (Suite suite : suites) {
				if (suite.getSuiteStatus() == Status.FAILURE) {
					amountOfFailed++;
				}
			}
			summaryData.setTotal(suites.size());
			summaryData.setAmountOfFailed(amountOfFailed);
			summaryData.setAmountOfSuccess(suites.size() - amountOfFailed);
		}

		return summaryData;
	}
}

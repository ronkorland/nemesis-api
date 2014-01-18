package com.nemesis.api.service;

import java.util.List;

import com.nemesis.api.data.SuiteData;
import com.nemesis.api.data.SuiteNameData;
import com.nemesis.api.data.SuitesData;
import com.nemesis.api.data.SummaryData;
import com.nemesis.api.data.chart.LineChart;
import com.nemesis.api.data.chart.PieChart;
import com.nemesis.api.filter.SuiteFilter;

public interface SuiteService {

	public SuiteData findById(String suiteId);

	public List<SuiteNameData> getSuiteNames();

	public SuiteData update(SuiteData suiteData);

	public SuiteData delete(SuiteData suiteData);

	public void createSuiteCollection();

	public void dropSuiteCollection();

	public SuiteData create(SuiteData suiteData);

	public SuitesData findAllSuites(SuiteFilter filter);

	public SuitesData findLast24HoursDistinct();
	
	public SuitesData findLast24Hours();
	
	public SummaryData findLast24HoursSummary();

	public PieChart getLast24HoursSuites();

	public PieChart getLast24HoursTests();

	public SuitesData findSuiteBefore(int days);

	public LineChart getAmountOfTestChart();
}

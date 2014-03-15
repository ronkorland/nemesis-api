package com.nemesis.api.resource;

import javax.ws.rs.core.Response;

import com.nemesis.api.data.test.TestData;

public interface TestResource {

	public Response getTestById(String testId);

	public Response updateTest(TestData testData);

	public Response deleteTest(TestData testData);

	public Response addTest(TestData testData);

	public Response getTestHistory(String testId);

	public Response getTests(int pageSize, int pageNumber, String method,
			String sortedBy, String sortDir, String status, int minusDays,
			String startDate, String endDate);

	public Response getTestsBySuiteId(String suiteId, int pageSize, int pageNumber,
			String method, String sortedBy, String sortDir, String status);

	public Response getMethodsBySuiteId(String suiteId);

	public Response getSuiteName();

	public Response getLast24HoursSummary();
}

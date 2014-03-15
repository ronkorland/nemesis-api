package com.nemesis.api.resource.impl;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nemesis.api.data.summary.SummaryData;
import com.nemesis.api.data.test.TestData;
import com.nemesis.api.data.test.TestHistoryListData;
import com.nemesis.api.data.test.TestMethodData;
import com.nemesis.api.data.test.TestsData;
import com.nemesis.api.filter.TestFilter;
import com.nemesis.api.resource.TestResource;
import com.nemesis.api.service.TestService;

@Path("/tests")
@Component
@Produces({ MediaType.APPLICATION_JSON })
public class TestResourceImpl implements TestResource {

	@Autowired
	private TestService testService;

	@GET
	@Path("/{testId}")
	@Override
	public Response getTestById(@PathParam("testId") String testId) {
		TestData data = testService.findById(testId);
		return Response.ok(data).build();
	}

	@GET
	@Override
	public Response getTests(@QueryParam("pageSize") int pageSize,
			@QueryParam("pageNumber") int pageNumber,
			@QueryParam("method") String method,
			@QueryParam("sortedBy") String sortedBy,
			@QueryParam("sortDir") String sortDir,
			@QueryParam("status") String status,
			@QueryParam("minusDays") int minusDays,
			@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate) {
		TestFilter filter = new TestFilter(pageSize, pageNumber, sortedBy,
				sortDir, method, status, minusDays, startDate);
		TestsData data = testService.findTests(filter);
		return Response.ok(data).build();
	}

	@GET
	@Path("/suite/{suiteId}")
	@Override
	public Response getTestsBySuiteId(@PathParam("suiteId") String suiteId,
			@QueryParam("pageSize") int pageSize,
			@QueryParam("pageNumber") int pageNumber,
			@QueryParam("method") String method,
			@QueryParam("sortedBy") String sortedBy,
			@QueryParam("sortDir") String sortDir,
			@QueryParam("status") String status) {
		TestFilter filter = new TestFilter(pageSize, pageNumber, sortedBy,
				sortDir, method, status, suiteId);
		TestsData data = testService.findTests(filter);
		return Response.ok(data).build();
	}

	@GET
	@Path("/suite/{suiteId}/method")
	@Override
	public Response getMethodsBySuiteId(@PathParam("suiteId") String suiteId) {
		List<TestMethodData> datas = testService.getMethodsBySuiteId(suiteId);
		if (datas != null) {
			return Response.ok(datas).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/method")
	@Override
	public Response getSuiteName() {
		List<TestMethodData> methods = testService.getMethods();
		return Response.ok(methods).build();
	}

	@PUT
	@Override
	public Response updateTest(TestData testData) {
		TestData data = testService.update(testData);
		return Response.ok(data).build();
	}

	@DELETE
	@Override
	public Response deleteTest(TestData testData) {
		TestData data = testService.delete(testData);
		return Response.ok(data).build();
	}

	@POST
	@Override
	public Response addTest(TestData testData) {
		TestData data = testService.create(testData);
		return Response.ok(data).build();
	}

	@GET
	@Path("/last/24/summary")
	@Override
	public Response getLast24HoursSummary() {
		SummaryData summaryData = testService.findLast24HoursSummary();
		return Response.ok(summaryData).build();
	}

	@GET
	@Path("/{testId}/history")
	@Override
	public Response getTestHistory(@PathParam("testId") String testId) {
		TestHistoryListData historyListData = testService
				.getTestHistory(testId);
		if (historyListData == null) {
			Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(historyListData).build();
	}
}

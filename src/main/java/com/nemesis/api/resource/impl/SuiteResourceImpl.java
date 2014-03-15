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

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nemesis.api.data.suite.SuiteData;
import com.nemesis.api.data.suite.SuiteNameData;
import com.nemesis.api.data.suite.SuitesData;
import com.nemesis.api.data.summary.SummaryData;
import com.nemesis.api.filter.SuiteFilter;
import com.nemesis.api.resource.SuiteResource;
import com.nemesis.api.service.SuiteService;

@Path("/suites")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class SuiteResourceImpl implements SuiteResource {

	@Autowired
	private SuiteService suiteService;

	@GET
	@Path("/names")
	@Override
	public Response getSuiteName() {
		List<SuiteNameData> names = suiteService.getSuiteNames();
		return Response.ok(names).build();
	}

	@GET
	@Override
	public Response getSuites(@QueryParam("pageSize") int pageSize,
			@QueryParam("pageNumber") int pageNumber,
			@QueryParam("suiteName") String suiteName,
			@QueryParam("sortedBy") String sortedBy,
			@QueryParam("sortDir") String sortDir,
			@QueryParam("status") String status,
			@QueryParam("minusDays") int minusDays) throws JSONException {

		SuiteFilter filter = new SuiteFilter(pageSize, pageNumber, sortedBy,
				sortDir, suiteName, minusDays, status);

		SuitesData suiteDataList = suiteService.findAllSuites(filter);
		return Response.ok(suiteDataList).build();
	}

	@GET
	@Path("/last/24/distinct")
	@Override
	public Response getLast24HoursDistinct() {
		SuitesData suiteDataList = suiteService.findLast24HoursDistinct();
		return Response.ok(suiteDataList).build();
	}

	@GET
	@Path("/last/24")
	@Override
	public Response getLast24Hours() {
		SuitesData suiteDataList = suiteService.findLast24Hours();
		return Response.ok(suiteDataList).build();
	}

	@GET
	@Path("/last/24/summary")
	@Override
	public Response getLast24HoursSummary() {
		SummaryData summaryData = suiteService.findLast24HoursSummary();
		return Response.ok(summaryData).build();
	}

	@GET
	@Path("/before/{days}")
	@Override
	public Response findSuitesBeforeXDays(@PathParam("days") int days) {
		SuitesData suiteDataList = suiteService.findSuiteBefore(days);
		return Response.ok(suiteDataList).build();
	}

	@GET
	@Path("/{suiteId}")
	@Override
	public Response getSuiteById(@PathParam("suiteId") String suiteId) {
		SuiteData suiteData = suiteService.findById(suiteId);
		return Response.ok(suiteData).build();
	}

	@POST
	@Override
	public Response createSuite(SuiteData suiteData) {
		SuiteData returnData = suiteService.create(suiteData);
		return Response.ok(returnData).build();
	}

	@PUT
	@Override
	public Response updateSuite(SuiteData suiteData) {
		SuiteData data = suiteService.update(suiteData);
		return Response.ok(data).build();
	}

	@DELETE
	@Override
	public Response deleteSuite(SuiteData suiteData) {
		SuiteData returnData = suiteService.delete(suiteData);
		return Response.ok(returnData).build();
	}
}

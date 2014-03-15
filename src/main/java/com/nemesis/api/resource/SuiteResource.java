package com.nemesis.api.resource;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import com.nemesis.api.data.suite.SuiteData;

public interface SuiteResource {

	public Response getSuiteName();

	public Response getSuites(@QueryParam("pageSize") int pageSize,
			@QueryParam("pageNumber") int pageNumber,
			@QueryParam("suiteName") String suiteName,
			@QueryParam("sortedBy") String sortedBy,
			@QueryParam("sortDir") String sortDir,
			@QueryParam("status") String status,
			@QueryParam("minusDays") int minusDays) throws JSONException;

	public Response getLast24Hours();

	public Response findSuitesBeforeXDays(@PathParam("days") int days);

	public Response getSuiteById(@PathParam("suiteId") String suiteId);

	public Response createSuite(SuiteData suiteData);

	public Response updateSuite(SuiteData suiteData);

	public Response deleteSuite(SuiteData suiteData);

	public Response getLast24HoursDistinct();

	public Response getLast24HoursSummary();

}

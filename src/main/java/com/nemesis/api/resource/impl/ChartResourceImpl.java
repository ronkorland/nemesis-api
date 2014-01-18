package com.nemesis.api.resource.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nemesis.api.data.chart.LineChart;
import com.nemesis.api.data.chart.PieChart;
import com.nemesis.api.resource.ChartResource;
import com.nemesis.api.service.SuiteService;

@Path("/suite/chart")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class ChartResourceImpl implements ChartResource {

	@Autowired
	private SuiteService suiteService;

	@GET
	@Path("/pie/suites")
	public Response getLast45HoursSuites() {
		PieChart chart = suiteService.getLast24HoursSuites();
		return Response.ok(chart).build();
	}

	@GET
	@Path("/pie/tests")
	public Response getLast45HoursTests() {
		PieChart chart = suiteService.getLast24HoursTests();
		return Response.ok(chart).build();
	}

	@GET
	@Path("/line/tests")
	public Response getAmountOfTestChart() {
		LineChart chart = suiteService.getAmountOfTestChart();
		return Response.ok(chart).build();
	}

}

package com.nemesis.api.resource;

import javax.ws.rs.core.Response;

public interface ChartResource {

	public Response getLast45HoursSuites();

	public Response getLast45HoursTests();

	public Response getAmountOfTestChart();

}

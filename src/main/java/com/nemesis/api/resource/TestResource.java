package com.nemesis.api.resource;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.nemesis.api.data.TestData;

public interface TestResource {

	public Response getTestById(@PathParam("testId") String testId);

	public Response updateTest(TestData testData);

	public Response deleteTest(TestData testData);

	public Response addTest(TestData testData);
}

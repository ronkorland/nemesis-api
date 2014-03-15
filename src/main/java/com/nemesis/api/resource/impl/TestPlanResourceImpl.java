package com.nemesis.api.resource.impl;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nemesis.api.data.test.TestPlanData;
import com.nemesis.api.data.test.TestPlansData;
import com.nemesis.api.resource.TestPlanResource;
import com.nemesis.api.service.TestPlanService;

@Path("/testplans")
@Component
@Produces({ MediaType.APPLICATION_JSON })
public class TestPlanResourceImpl implements TestPlanResource {

	@Autowired
	private TestPlanService testPlanService;

	@POST
	@Override
	public Response create(TestPlanData testPlanData) {
		TestPlanData create = testPlanService.create(testPlanData);
		return Response.ok(create).build();
	}

	@PUT
	@Override
	public Response update(TestPlanData testPlanData) {
		TestPlanData update = testPlanService.update(testPlanData);
		return Response.ok(update).build();
	}

	@GET
	@Path("/{testPlanId}")
	@Override
	public Response findById(@PathParam("testPlanId") String testPlanId) {
		TestPlanData testPlanData = testPlanService.findById(testPlanId);
		return Response.ok(testPlanData).build();
	}

	@DELETE
	@Path("/{testPlanId}")
	@Override
	public Response delete(@PathParam("testPlanId") String testPlanId) {
		TestPlanData testPlanData = testPlanService.delete(testPlanId);
		return Response.ok(testPlanData).build();
	}

	@GET
	@Override
	public Response getAllTestPlans() {
		TestPlansData testPlansData = testPlanService.getAllTestPlans();
		return Response.ok(testPlansData).build();
	}

}

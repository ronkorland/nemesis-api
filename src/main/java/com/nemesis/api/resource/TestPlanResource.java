package com.nemesis.api.resource;

import javax.ws.rs.core.Response;

import com.nemesis.api.data.TestPlanData;

public interface TestPlanResource {

	public Response create(TestPlanData testPlanData);

	public Response findById(String testPlanId);

	public Response getAllTestPlans();

	public Response update(TestPlanData testPlanData);

	public Response delete(String testPlanId);

}

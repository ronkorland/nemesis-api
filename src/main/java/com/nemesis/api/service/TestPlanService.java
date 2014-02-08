package com.nemesis.api.service;

import com.nemesis.api.data.TestPlanData;
import com.nemesis.api.data.TestPlansData;

public interface TestPlanService {

	public TestPlanData create(TestPlanData testPlanData);

	public TestPlanData findById(String testPlanId);

	public TestPlanData delete(String testPlanId);

	public TestPlanData update(TestPlanData testPlanData);

	public TestPlansData getAllTestPlans();
}

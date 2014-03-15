package com.nemesis.api.service;

import com.nemesis.api.data.test.TestPlanData;
import com.nemesis.api.data.test.TestPlansData;

public interface TestPlanService {

	public TestPlanData create(TestPlanData testPlanData);

	public TestPlanData findById(String testPlanId);

	public TestPlanData delete(String testPlanId);

	public TestPlanData update(TestPlanData testPlanData);

	public TestPlansData getAllTestPlans();
}

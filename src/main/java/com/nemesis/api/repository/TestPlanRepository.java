package com.nemesis.api.repository;

import java.util.List;

import com.nemesis.api.model.TestPlan;

public interface TestPlanRepository {

	public TestPlan create(TestPlan testPlan);
		
	public TestPlan delete(TestPlan testPlan);
	
	public List<TestPlan> getAllTestPlans();
	
	public TestPlan findById(String testPlanId);
	
	public List<TestPlan> findByTestId(String testId);
	
	public List<TestPlan> findBySuiteId(String suiteId);

	public TestPlan save(TestPlan testPlan);
}

package com.nemesis.api.repository;

import java.util.List;

import com.nemesis.api.model.TestPlan;

public interface TestPlanRepository extends Repository<TestPlan, String> {

	public List<TestPlan> findByTestId(String testId);

	public List<TestPlan> findBySuiteId(String suiteId);
}

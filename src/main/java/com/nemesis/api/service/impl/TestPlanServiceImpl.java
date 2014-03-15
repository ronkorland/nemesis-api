package com.nemesis.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nemesis.api.data.test.TestPlanData;
import com.nemesis.api.data.test.TestPlansData;
import com.nemesis.api.model.TestPlan;
import com.nemesis.api.repository.TestPlanRepository;
import com.nemesis.api.service.TestPlanService;

@Service("testPlanService")
@Scope("singleton")
public class TestPlanServiceImpl implements TestPlanService {

	@Autowired
	private TestPlanRepository testPlanRepository;

	@Override
	public TestPlanData create(TestPlanData testPlanData) {
		TestPlan testPlan = new TestPlan(testPlanData);
		testPlanRepository.create(testPlan);
		return testPlanData;
	}

	@Override
	public TestPlanData findById(String testPlanId) {
		TestPlan testPlan = testPlanRepository.findById(testPlanId);
		TestPlanData testPlanData = new TestPlanData(testPlan);
		return testPlanData;
	}

	@Override
	public TestPlanData delete(String testPlanId) {
		TestPlanData testPlanData = findById(testPlanId);
		TestPlan testPlan = new TestPlan(testPlanData);
		testPlanRepository.delete(testPlan);
		return testPlanData;
	}

	@Override
	public TestPlanData update(TestPlanData testPlanData) {
		if (StringUtils.isNotBlank(testPlanData.getId())) {
			TestPlan currentTestPlan = new TestPlan(testPlanData);
			TestPlan oldTestPlan = new TestPlan(findById(testPlanData.getId()));

			oldTestPlan = oldTestPlan.merge(currentTestPlan);
			oldTestPlan.setUpdateDateTime(LocalDateTime.now());

			TestPlan updatedTestPlan = testPlanRepository.save(oldTestPlan);
			TestPlanData updatedtestPlanData = new TestPlanData(updatedTestPlan);
			return updatedtestPlanData;
		}
		return null;

	}

	@Override
	public TestPlansData getAllTestPlans() {
		List<TestPlan> testPlans = testPlanRepository.getAllTestPlans();
		List<TestPlanData> testPlanDatas = new ArrayList<TestPlanData>();
		if (testPlans != null && testPlans.size() > 0) {
			for (TestPlan testPlan : testPlans) {
				testPlanDatas.add(new TestPlanData(testPlan));
			}
		}
		TestPlansData data = new TestPlansData();
		data.setTestPlans(testPlanDatas);
		data.setTotal(testPlanDatas.size());
		return data;
	}

}

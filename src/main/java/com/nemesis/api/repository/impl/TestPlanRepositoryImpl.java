package com.nemesis.api.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.nemesis.api.model.TestPlan;
import com.nemesis.api.repository.TestPlanRepository;

@Repository
@Scope("singleton")
public class TestPlanRepositoryImpl implements TestPlanRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public TestPlan create(TestPlan testPlan) {
		mongoTemplate.insert(testPlan);
		return testPlan;
	}
	
	@Override
	public TestPlan save(TestPlan testPlan){
		mongoTemplate.save(testPlan);
		return testPlan;
	}

	@Override
	public TestPlan delete(TestPlan testPlan) {
		mongoTemplate.remove(testPlan);
		return testPlan;
	}

	@Override
	public List<TestPlan> getAllTestPlans() {
		return mongoTemplate.findAll(TestPlan.class);
	}

	@Override
	public TestPlan findById(String testPlanId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(testPlanId));
		return mongoTemplate.findOne(query, TestPlan.class);
	}

	@Override
	public List<TestPlan> findByTestId(String testId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("testIds").is(testId));
		return mongoTemplate.find(query, TestPlan.class);
	}

	@Override
	public List<TestPlan> findBySuiteId(String suiteId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("suiteIds").is(suiteId));
		return mongoTemplate.find(query, TestPlan.class);	}

}

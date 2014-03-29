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
public class TestPlanRepositoryImpl extends RepositoryImpl<TestPlan, String>
		implements TestPlanRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public TestPlanRepositoryImpl(){
		super(TestPlan.class);
	}

	public TestPlanRepositoryImpl(Class<TestPlan> entityClass) {
		super(TestPlan.class);
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
		return mongoTemplate.find(query, TestPlan.class);
	}

}

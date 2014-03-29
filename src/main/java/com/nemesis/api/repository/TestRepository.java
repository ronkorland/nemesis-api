package com.nemesis.api.repository;

import java.util.List;

import com.nemesis.api.filter.TestFilter;
import com.nemesis.api.filter.TestHistoryFilter;
import com.nemesis.api.model.Test;

public interface TestRepository extends MongoRepository<Test, String> {

	public void addRefToTestAttach(String testId, String attachId);

	public Test update(Test test);

	public List<Test> findTests(TestFilter filter);

	public List<String> getMethods();

	public long count(TestFilter filter);

	public void deleteRefTestAttach(String attachId);

	public List<String> getMethodsBySuiteId(String suiteId);

	public List<Test> findLast24Hours();

	public List<Test> getTestHistory(TestHistoryFilter filter);
}

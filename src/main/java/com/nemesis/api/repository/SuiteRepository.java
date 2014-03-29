package com.nemesis.api.repository;

import java.util.List;

import com.nemesis.api.filter.SuiteFilter;
import com.nemesis.api.model.Suite;

public interface SuiteRepository extends MongoRepository<Suite, String> {

	public List<Suite> findAllSuites(SuiteFilter filter);

	public List<String> getSuiteNames();

	public Suite update(Suite suite);

	public long count(SuiteFilter filter);

	public List<Suite> findLast24Hours();

	public long countLast24Hours();

	public List<Suite> findSuiteBefore(int days);
}

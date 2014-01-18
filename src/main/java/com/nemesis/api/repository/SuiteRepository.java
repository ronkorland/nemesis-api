package com.nemesis.api.repository;

import java.util.List;

import com.nemesis.api.filter.SuiteFilter;
import com.nemesis.api.model.Suite;

public interface SuiteRepository {

	public List<Suite> findAllSuites(SuiteFilter filter);

	public List<Suite> findAllSuites();

	public Suite findById(String suiteId);

	public List<String> getSuiteNames();

	public Suite create(Suite suite);

	public Suite update(Suite suite);

	public Suite delete(Suite suite);

	public Suite save(Suite suite);

	public long count(SuiteFilter filter);

	public List<Suite> findLast24Hours();

	public long countLast24Hours();

	public List<Suite> findSuiteBefore(int days);

	public void createSuiteCollection();

	public void dropSuiteCollection();
}

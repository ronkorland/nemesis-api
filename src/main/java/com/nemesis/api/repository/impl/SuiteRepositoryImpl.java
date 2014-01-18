package com.nemesis.api.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCollection;
import com.nemesis.api.filter.SuiteFilter;
import com.nemesis.api.model.Suite;
import com.nemesis.api.repository.SuiteRepository;

@Repository
@Scope("singleton")
public class SuiteRepositoryImpl implements SuiteRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	private Sort sort(String sortedBy, String sortDir) {
		return new Sort(Direction.fromString(sortDir), sortedBy);
	}

	private Criteria suiteNameCriteria(String suiteName) {
		return where("suiteName").is(suiteName);
	}

	private Criteria suiteStatus(String status) {
		return where("suiteStatus").is(status);
	}

	private Query lastStartTimeQuery(int minusDays) {
		Query query = new Query(lastStartTimeCriteria(minusDays));
		return query;
	}

	private Criteria lastStartTimeCriteria(int minusDays) {
		LocalDateTime date = new LocalDateTime();
		date = date.minusDays(minusDays);
		return where("startTime").gte(date.toDateTime(DateTimeZone.UTC));
	}

	@Override
	public long countLast24Hours() {
		return mongoTemplate.count(lastStartTimeQuery(1), Suite.class);
	}

	@Override
	public List<Suite> findAllSuites() {
		List<Suite> suites = mongoTemplate.findAll(Suite.class);
		return suites;
	}

	@Override
	public List<Suite> findLast24Hours() {
		List<Suite> suites = mongoTemplate.find(lastStartTimeQuery(1),
				Suite.class);
		return suites;
	}

	@Override
	public List<Suite> findSuiteBefore(int days) {
		LocalDateTime dateTime = new LocalDateTime();
		LocalDateTime startDateTime = new LocalDateTime();
		LocalDateTime endDateTime = new LocalDateTime();
		startDateTime = dateTime.minusDays(days);
		endDateTime = dateTime.minusDays(days);
		startDateTime = startDateTime.withTime(0, 0, 0, 0);
		endDateTime = endDateTime.withTime(23, 59, 59, 999);

		Query query = new Query(Criteria.where("startTime")
				.gte(startDateTime.toDateTime(DateTimeZone.UTC)).and("endTime")
				.lte(endDateTime));
		List<Suite> suites = mongoTemplate.find(query, Suite.class);
		return suites;
	}

	@Override
	public Suite findById(String suiteId) {
		Suite suite = mongoTemplate.findOne(
				new Query(Criteria.where("_id").is(suiteId)), Suite.class);
		return suite;
	}

	@Override
	public Suite create(Suite suite) {
		try {
			mongoTemplate.insert(suite);
			return suite;
		} catch (Throwable t) {
			return null;
		}
	}

	@Override
	public Suite update(Suite suite) {
		try {
			Suite findSuite = findById(suite.getId());
			Suite mergeSuite = findSuite.merge(suite);
			mongoTemplate.save(mergeSuite);
			return mergeSuite;
		} catch (Throwable t) {
			return null;
		}
	}

	@Override
	public Suite save(Suite suite) {
		try {
			mongoTemplate.save(suite);
			return suite;
		} catch (Throwable t) {
			return null;
		}
	}

	@Override
	public Suite delete(Suite suite) {
		mongoTemplate.remove(suite);
		return suite;
	}

	@Override
	public void createSuiteCollection() {
		if (!mongoTemplate.collectionExists(Suite.class)) {
			mongoTemplate.createCollection(Suite.class);
		}
	}

	@Override
	public void dropSuiteCollection() {
		if (mongoTemplate.collectionExists(Suite.class)) {
			mongoTemplate.dropCollection(Suite.class);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getSuiteNames() {
		DBCollection collection = mongoTemplate.getCollection("suites");
		List<String> list = collection.distinct("suiteName");
		return list;
	}

	private Query filterToQuery(SuiteFilter filter) {
		Criteria criteria = null;
		Sort sort = null;
		PageRequest pageRequest = null;

		if (filter.getMinusDays() > 0) {
			criteria = lastStartTimeCriteria(filter.getMinusDays());
			if (StringUtils.isNotBlank(filter.getStatus())) {
				criteria.and("suiteStatus").is(filter.getStatus());
			}
			if (StringUtils.isNotBlank(filter.getSuiteName())) {
				criteria.and("suiteName").is(filter.getSuiteName());
			}
		} else {
			if (StringUtils.isNotBlank(filter.getSuiteName())) {
				criteria = suiteNameCriteria(filter.getSuiteName());
				if (StringUtils.isNotBlank(filter.getStatus())) {
					criteria.andOperator(suiteStatus(filter.getStatus()));
				}
			} else {
				criteria = suiteStatus(filter.getStatus());
			}
		}

		if (StringUtils.isNotBlank(filter.getSortDir())
				&& StringUtils.isNotBlank(filter.getSortedBy())) {
			sort = sort(filter.getSortedBy(), filter.getSortDir());
		}
		if (filter.getPageSize() > 0 && filter.getPageSize() > 0) {
			pageRequest = new PageRequest(filter.getPageNumber(),
					filter.getPageSize());
		}

		Query query = new Query();
		if (criteria != null) {
			query.addCriteria(criteria);
		}
		query.with(pageRequest);
		query.with(sort);

		return query;

	}

	@Override
	public List<Suite> findAllSuites(SuiteFilter filter) {
		if (filter != null) {
			return mongoTemplate.find(filterToQuery(filter), Suite.class);
		}
		return null;
	}

	@Override
	public long count(SuiteFilter filter) {
		if (filter != null) {
			return mongoTemplate.count(filterToQuery(filter), Suite.class);
		}
		return 0;
	}
}

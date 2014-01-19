package com.nemesis.api.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import com.nemesis.api.filter.TestFilter;
import com.nemesis.api.filter.TestHistoryFilter;
import com.nemesis.api.model.Test;
import com.nemesis.api.repository.TestRepository;

@Repository
@Scope("singleton")
public class TestRepositoryImpl implements TestRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Test create(Test test) {
		try {
			mongoTemplate.insert(test);
			return test;
		} catch (Throwable t) {
			return null;
		}
	}

	@Override
	public Test delete(Test test) {
		mongoTemplate.remove(test);
		return test;
	}

	@Override
	public Test update(Test test) {
		Test findTest = findById(test.getId());
		Test mergeTest = findTest.merge(test);
		mongoTemplate.save(mergeTest);
		return test;
	}

	@Override
	public Test findById(String testId) {
		Test test = mongoTemplate.findOne(
				new Query(Criteria.where("_id").is(testId)), Test.class);
		return test;
	}

	@Override
	public void addRefToTestAttach(String testId, String attachId) {
		Test test = findById(testId);
		if (test != null) {
			if (test.getTestAttachments() == null) {
				List<String> attachList = new ArrayList<String>();
				attachList.add(attachId);
				test.setTestAttachments(attachList);
			} else {
				test.getTestAttachments().add(attachId);
			}
			update(test);
		} else {
			throw new NoSuchElementException("Failed to find test with id "
					+ testId);
		}
	}

	@Override
	public void deleteRefTestAttach(String attachId) {
		Test test = findByAttachId(attachId);
		List<String> attachs = test.getTestAttachments();
		if (attachs != null) {
			Iterator<String> i = attachs.iterator();
			while (i.hasNext()) {
				String s = i.next();
				if (s.equals(attachId)) {
					i.remove();
				}
			}
		}
		update(test);
	}

	private Test findByAttachId(String attachId) {
		Test test = mongoTemplate.findOne(
				Query.query(Criteria.where("testAttachments").is(attachId)),
				Test.class);
		return test;
	}

	private Sort sort(String sortedBy, String sortDir) {
		return new Sort(Direction.fromString(sortDir), sortedBy);
	}

	private Criteria methodCriteria(String method) {
		return where("method").is(method);
	}

	private Criteria testStatus(String status) {
		return where("testStatus").is(status);
	}

	private Query filterToQuery(TestFilter filter) {
		Criteria criteria = null;
		Sort sort = null;
		PageRequest pageRequest = null;

		if (filter.getMinusDays() > 0) {
			criteria = lastStartTimeCriteria(filter.getMinusDays());
			if (StringUtils.isNotBlank(filter.getStatus())) {
				criteria.and("testStatus").is(filter.getStatus());
			}
			if (StringUtils.isNotBlank(filter.getMethod())) {
				criteria.and("method").is(filter.getMethod());
			}
		} else if (filter.getMinusDays() == -1 && filter.getStartDate() != null) {
			DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MM-yyyy");
			LocalDate startDate = dtf.parseLocalDate(filter.getStartDate());

			criteria = where("startTime").gte(
					startDate.toDateTime(LocalTime.MIDNIGHT)).lt(
					startDate.toDateTime(new LocalTime(23, 59, 59)));

			if (StringUtils.isNotBlank(filter.getStatus())) {
				criteria.and("testStatus").is(filter.getStatus());
			}
			if (StringUtils.isNotBlank(filter.getMethod())) {
				criteria.and("method").is(filter.getMethod());
			}
		} else {
			if (StringUtils.isNotBlank(filter.getMethod())) {
				criteria = methodCriteria(filter.getMethod());
				if (StringUtils.isNotBlank(filter.getStatus())) {
					criteria.and("testStatus").is(filter.getStatus());
				}
			} else {
				criteria = testStatus(filter.getStatus());
			}
		}

		if (StringUtils.isNotBlank(filter.getSuiteId()) && criteria != null) {
			criteria.and("suiteId").is(filter.getSuiteId());
		} else if ((StringUtils.isNotBlank(filter.getSuiteId()) && criteria == null)) {
			criteria = Criteria.where("suiteId").is(filter.getSuiteId());
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
	@SuppressWarnings("unchecked")
	public List<String> getMethodsBySuiteId(String suiteId) {
		DBCollection collection = mongoTemplate.getCollection("tests");
		List<String> list = collection.distinct("method",
				Query.query(Criteria.where("suiteId").is(suiteId))
						.getQueryObject());
		return list;
	}

	@Override
	public long count(TestFilter filter) {
		if (filter != null) {
			return mongoTemplate.count(filterToQuery(filter), Test.class);
		}
		return 0;
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
	public List<Test> findLast24Hours() {
		List<Test> tests = mongoTemplate
				.find(lastStartTimeQuery(1), Test.class);
		return tests;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getMethods() {
		DBCollection collection = mongoTemplate.getCollection("tests");
		List<String> list = collection.distinct("method");
		return list;
	}

	@Override
	public List<Test> findTests(TestFilter filter) {
		if (filter != null) {
			return mongoTemplate.find(filterToQuery(filter), Test.class);
		}
		return null;
	}

	@Override
	public List<Test> getTestHistory(TestHistoryFilter filter) {
		if (filter.isEmpty()) {
			return null;
		}

		Criteria criteria = where("className").is(filter.getClassName())
				.and("method").is(filter.getMethod());
		
		Query query = new Query();
		query.addCriteria(criteria);
		query.with(sort("startTime", "desc"));
		query.limit(20);
		return mongoTemplate.find(query, Test.class);
	}
}

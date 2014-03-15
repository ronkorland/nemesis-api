package com.nemesis.api.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.nemesis.api.model.Sprint;
import com.nemesis.api.repository.SprintRepository;

@Repository
@Scope("singleton")
public class SprintRepositoryImpl implements SprintRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Sprint> findAllSprints() {
		List<Sprint> sprints = mongoTemplate.findAll(Sprint.class);
		return sprints;
	}

	@Override
	public Sprint create(Sprint sprint) {
		mongoTemplate.insert(sprint);
		return sprint;
	}

	@Override
	public Sprint delete(Sprint sprint) {
		mongoTemplate.remove(sprint);
		return sprint;
	}

	@Override
	public Sprint findById(String sprintId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(sprintId));
		return mongoTemplate.findOne(query, Sprint.class);
	}

	@Override
	public Sprint save(Sprint sprint) {
		mongoTemplate.save(sprint);
		return sprint;
	}

}

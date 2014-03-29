package com.nemesis.api.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.nemesis.api.model.BaseModel;
import com.nemesis.api.repository.MongoRepository;

@Repository
@Scope("singleton")
public class MongoRepositoryImpl<M extends BaseModel, I> implements
		MongoRepository<M, I> {

	private Class<M> modelClass;

	@Autowired
	private MongoTemplate mongoTemplate;

	public MongoRepositoryImpl() {
		super();
	}

	public MongoRepositoryImpl(Class<M> modelClass) {
		this.modelClass = modelClass;
	}

	@Override
	public M create(M model) {
		if (model != null) {
			mongoTemplate.insert(model);
			return model;
		}
		return null;
	}

	@Override
	public M save(M model) {
		if (model != null) {
			mongoTemplate.save(model);
			return model;
		}
		return null;
	}

	@Override
	public M delete(M model) {
		mongoTemplate.remove(model);
		return model;
	}

	@Override
	public M findOne(Query query) {
		M findOne = mongoTemplate.findOne(query, this.modelClass);
		return findOne;
	}

	@Override
	public M findById(I id) {
		Query query = new Query(Criteria.where("_id").is(id));

		M findOne = findOne(query);
		return findOne;
	}

	@Override
	public List<M> findAll() {
		List<M> findAll = mongoTemplate.findAll(this.modelClass);
		return findAll;
	}
}

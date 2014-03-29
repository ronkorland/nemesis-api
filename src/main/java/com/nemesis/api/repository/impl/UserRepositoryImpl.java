package com.nemesis.api.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.nemesis.api.model.User;
import com.nemesis.api.repository.UserRepository;

@Repository
@Scope("singleton")
public class UserRepositoryImpl extends MongoRepositoryImpl<User, String> implements
		UserRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	public UserRepositoryImpl() {
		super(User.class);
	}

	public UserRepositoryImpl(Class<User> entityClass) {
		super(User.class);
	}

	@Override
	public User findUserByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));

		User user = mongoTemplate.findOne(query, User.class);
		return user;
	}
}

package com.nemesis.api.repository.impl;

import java.util.List;

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
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public User findUserByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username));

		User user = mongoTemplate.findOne(query, User.class);
		return user;
	}

	@Override
	public User createUser(User user) {
		if (user != null) {
			mongoTemplate.insert(user);
		}
		return user;
	}

	@Override
	public User save(User user) {
		if (user != null) {
			mongoTemplate.save(user);
		}
		return null;
	}

	@Override
	public List<User> getUsers() {
		List<User> all = mongoTemplate.findAll(User.class);
		return all;
	}

	@Override
	public User findById(String userId) {
		Query query = new Query(Criteria.where("_id").is(userId));

		User user = mongoTemplate.findOne(query, User.class);
		return user;
	}

	@Override
	public User delete(User user) {
		mongoTemplate.remove(user);
		return user;
	}

}

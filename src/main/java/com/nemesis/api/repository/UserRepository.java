package com.nemesis.api.repository;

import java.util.List;

import com.nemesis.api.model.User;

public interface UserRepository {

	public User findUserByUsername(String username);
	
	public User findById(String userId);

	public User createUser(User user);

	public User save(User user);

	public List<User> getUsers();
	
	public User delete(User user);
}

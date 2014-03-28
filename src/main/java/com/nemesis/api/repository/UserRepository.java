package com.nemesis.api.repository;

import com.nemesis.api.model.User;

public interface UserRepository {

	public User findUserByUsername(String username);

	public User createUser(User user);

	public User save(User user);
}

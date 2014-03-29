package com.nemesis.api.repository;

import com.nemesis.api.model.User;

public interface UserRepository extends Repository<User, String> {

	public User findUserByUsername(String username);
}

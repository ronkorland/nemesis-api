package com.nemesis.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nemesis.api.data.user.UserData;

public interface UserService extends UserDetailsService {

	public UserData createUser(UserData userData);

	public UserData addRole(String username, String role);

}

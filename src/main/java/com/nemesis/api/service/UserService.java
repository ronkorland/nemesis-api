package com.nemesis.api.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nemesis.api.data.user.MapPermissions;
import com.nemesis.api.data.user.UserData;
import com.nemesis.api.data.user.UsersData;

public interface UserService extends UserDetailsService {

	public UserData createUser(UserData userData);

	public UserData changePermissions(MapPermissions mapPermissions);
	
	public UsersData getUsers();

	public UserData delete(String userId);

}

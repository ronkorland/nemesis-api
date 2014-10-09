package com.nemesis.api.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nemesis.api.data.user.MapPermissions;
import com.nemesis.api.data.user.UserData;
import com.nemesis.api.data.user.UsersData;
import com.nemesis.api.model.User;

public interface UserService extends UserDetailsService {

	public UserData createUser(UserData userData);

	public UserData changePermissions(MapPermissions mapPermissions);

	public UsersData getUsers();

	public UserData delete(String userId);

	public void changePassword(String userId, String currentPassword, String newPassword);

	public UserData update(UserData data);

	public List<User> findUserDailyReport();

}

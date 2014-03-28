package com.nemesis.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nemesis.api.data.user.UserData;
import com.nemesis.api.model.User;
import com.nemesis.api.repository.UserRepository;
import com.nemesis.api.service.UserService;

@Service("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Failed to find username "
					+ username);
		}

		return new UserData(user);
	}

	@Override
	public UserData createUser(UserData userData) {
		userData.setPassword(passwordEncoder.encode(userData.getPassword()));
		userRepository.createUser(new User(userData));
		return userData;
	}

	@Override
	public UserData addRole(String username, String role) {
		User user = userRepository.findUserByUsername(username);
		user.addRole(role);
		userRepository.save(user);
		return new UserData(user);
	}
}

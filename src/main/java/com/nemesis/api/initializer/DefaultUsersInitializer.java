package com.nemesis.api.initializer;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.nemesis.api.model.User;
import com.nemesis.api.repository.UserRepository;

public class DefaultUsersInitializer {

	private PasswordEncoder passwordEncoder;

	private UserRepository userRepository;

	public DefaultUsersInitializer() {
		super();
	}

	public DefaultUsersInitializer(PasswordEncoder passwordEncoder,
			UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	public void initDefaultUsers() {
		User user = userRepository.findUserByUsername("admin");
		if (user == null) {
			User newUser = new User();
			newUser.setUsername("admin");
			newUser.setPassword(this.passwordEncoder
					.encode("L3q1T6wM8G8kjqkdsWg58L0775aQ6K"));
			newUser.addPermission("admin");
			newUser.addPermission("create");
			newUser.addPermission("view");
			newUser.addPermission("edit");
			newUser.addPermission("delete");
			userRepository.create(newUser);
		}
	}

}

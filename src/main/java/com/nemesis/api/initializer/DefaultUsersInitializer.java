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

	public DefaultUsersInitializer(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	public void initDefaultUsers() {
		User admin = userRepository.findUserByUsername("admin");
		if (admin == null) {
			User newUser = new User();
			newUser.setUsername("admin");
			newUser.setPassword(this.passwordEncoder.encode("L3q1T6wM8G8kjqkdsWg58L0775aQ6K"));
			newUser.addPermission("admin");
			newUser.addPermission("create");
			newUser.addPermission("view");
			newUser.addPermission("edit");
			newUser.addPermission("delete");
			userRepository.create(newUser);
		}

		User api = userRepository.findUserByUsername("api");
		if (api == null) {
			User newUser = new User();
			newUser.setUsername("api");
			newUser.setPassword(this.passwordEncoder.encode("muVucuz7"));
			newUser.addPermission("create");
			newUser.addPermission("view");
			newUser.addPermission("edit");
			newUser.addPermission("delete");
			userRepository.create(newUser);
		}

	}

}

package com.nemesis.api.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nemesis.api.data.user.UserData;

@Document(collection = "users")
public class User extends BaseModel {

	private String username;

	private String password;

	private List<String> roles;

	public User() {
		super();
	}

	public User(UserData data) {
		super(data);
		setUsername(data.getUsername());
		setPassword(data.getPassword());
		setRoles(data.getRoles());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		if (roles == null) {
			roles = new ArrayList<String>();
		}
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void addRole(String role) {
		getRoles().add(role);
	}

}

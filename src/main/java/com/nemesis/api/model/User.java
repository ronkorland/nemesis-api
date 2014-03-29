package com.nemesis.api.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.nemesis.api.data.user.UserData;

@Document(collection = "users")
public class User extends BaseModel {

	private String username;

	private String password;

	private String email;

	private List<String> permissions;

	public User() {
		super();
	}

	public User(UserData data) {
		super(data);
		setUsername(data.getUsername());
		setPassword(data.getPassword());
		setPermissions(data.getPermissions());
		setEmail(data.getEmail());
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

	public void addPermission(String permission) {
		getPermissions().add(permission);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPermissions() {
		if (permissions == null) {
			permissions = new ArrayList<String>();
		}
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

}

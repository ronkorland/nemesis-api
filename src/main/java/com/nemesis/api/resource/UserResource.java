package com.nemesis.api.resource;

import javax.ws.rs.core.Response;

import com.nemesis.api.data.user.UserData;

public interface UserResource {

	public Response login(UserData data);

	public Response logout();

	public Response ping();

	public Response createUser(UserData data);

	public Response addRole(String username, String role);

	public Response getUser();

}

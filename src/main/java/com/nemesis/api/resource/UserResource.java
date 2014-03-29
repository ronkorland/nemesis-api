package com.nemesis.api.resource;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import com.nemesis.api.data.user.MapPermissions;
import com.nemesis.api.data.user.UserData;

public interface UserResource {

	public Response login(UserData data);

	public Response createUser(UserData data) throws JSONException;

	public Response changePermissions(MapPermissions mapPermissions);

	public Response getUser();
	
	public Response getUsers();

	public Response delete(String userId);

}

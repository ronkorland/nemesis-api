package com.nemesis.api.resource.impl;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nemesis.api.data.user.MapPermissions;
import com.nemesis.api.data.user.TokenData;
import com.nemesis.api.data.user.UserData;
import com.nemesis.api.data.user.UsersData;
import com.nemesis.api.exception.PasswordDoesntMatchException;
import com.nemesis.api.exception.UsernameAlreadyExistsException;
import com.nemesis.api.resource.UserResource;
import com.nemesis.api.service.UserService;
import com.nemesis.api.util.TokenUtils;

@Path("/users")
@Component
@Produces({ MediaType.APPLICATION_JSON })
public class UserResourceImpl implements UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	@Override
	@Path("/login")
	@POST
	public Response login(UserData data) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				data.getUsername(), data.getPassword());
		try {
			Authentication authentication = authManager
					.authenticate(authenticationToken);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);

			/*
			 * Reload user as password of authentication principal will be null
			 * after authorization and password is needed for token generation
			 */
			UserData userData = (UserData) userService.loadUserByUsername(data
					.getUsername());
			return Response.ok(new TokenData(TokenUtils.createToken(userData)))
					.build();
		} catch (AuthenticationException e) {
			return Response.status(401).build();
		}

	}

	@Override
	@POST
	public Response createUser(UserData data) throws JSONException {
		try {
			UserData retUserData = userService.createUser(data);
			return Response.ok(retUserData).build();
		} catch (UsernameAlreadyExistsException e) {
			JSONObject object = new JSONObject();
			object.append("message", e.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(object).build();
		}

	}

	@Override
	@POST
	@Path("/permissions")
	public Response changePermissions(MapPermissions mapPermissions) {
		UserData userData = userService.changePermissions(mapPermissions);
		return Response.ok(userData).build();
	}

	@Override
	@GET
	@Path("/current")
	public Response getUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String
				&& ((String) principal).equals("anonymousUser")) {
			throw new WebApplicationException(401);
		}
		UserDetails userDetails = (UserDetails) principal;

		return Response.ok(userDetails).build();
	}

	@Override
	@GET
	public Response getUsers() {
		UsersData usersData = userService.getUsers();
		return Response.ok(usersData).build();
	}

	@Override
	@DELETE
	@Path("/{userId}")
	public Response delete(@PathParam("userId") String userId) {
		UserData userData = userService.delete(userId);

		return Response.ok(userData).build();
	}

	@Override
	@POST
	@Path("/changepassword")
	public Response chnagePassword(@FormParam("userId") String userId,
			@FormParam("currentPassword") String currentPassword,
			@FormParam("newPassword") String newPassword) throws JSONException {
		JSONObject object = new JSONObject();
		try {
			userService.changePassword(userId, currentPassword, newPassword);
			object.append("message", "Password changed");
			return Response.ok(object).build();
		} catch (PasswordDoesntMatchException e) {
			object.append("message", e.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(object).build();
		}
	}

}

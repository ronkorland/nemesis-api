package com.nemesis.api.resource.impl;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nemesis.api.data.user.TokenData;
import com.nemesis.api.data.user.UserData;
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
	public Response createUser(UserData data) {
		UserData retUserData = userService.createUser(data);
		return Response.ok(retUserData).build();
	}

	@Override
	@Path("/logout")
	@GET
	public Response logout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response ping() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@POST
	@Path("/role")
	public Response addRole(@FormParam("username") String username,
			@FormParam("role") String role) {
		UserData userData = userService.addRole(username, role);
		return Response.ok(userData).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getUser() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String
				&& ((String) principal).equals("anonymousUser")) {
			throw new WebApplicationException(401);
		}
		UserDetails userDetails = (UserDetails) principal;
		// UserData data = (UserData) userDetails;

		return Response.ok(userDetails).build();
	}

}

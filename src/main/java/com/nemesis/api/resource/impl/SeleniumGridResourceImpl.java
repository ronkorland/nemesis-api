package com.nemesis.api.resource.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nemesis.api.constants.ActionType;
import com.nemesis.api.resource.SeleniumGridResource;
import com.nemesis.api.service.SeleniumGridService;

@Path("/grid")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class SeleniumGridResourceImpl implements SeleniumGridResource {

	@Autowired
	private SeleniumGridService seleniumGridService;

	@GET
	@Path("/node/action")
	@Override
	public Response nodeAction(@QueryParam("actionType") String actionType,
			@QueryParam("remoteHost") String nodeRemoteHost) {
		ActionType eActionType = ActionType.getEnum(actionType);
		String nodeAction = seleniumGridService.nodeAction(eActionType, nodeRemoteHost);
		return Response.ok(nodeAction).build();
	}
	
	@GET
	@Path("/hub")
	@Override
	public Response getHubInfo() {
		JSONObject hubInfo = seleniumGridService.getHubInfo();
		return Response.ok(hubInfo).build();
	}

	@GET
	@Path("/node/info")
	@Override
	public Response reloadAllNodeInfo() {
		boolean status = seleniumGridService.reloadAllNodeInfo();
		if (status) {
			return Response.ok("{stauts: \"OK\"}").build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}

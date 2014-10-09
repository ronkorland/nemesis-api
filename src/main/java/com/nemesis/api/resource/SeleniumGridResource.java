package com.nemesis.api.resource;

import javax.ws.rs.core.Response;

public interface SeleniumGridResource {

	public Response nodeAction(String actionType, String nodeRemoteHost);

	public Response reloadAllNodeInfo();

	public Response getHubInfo();
}

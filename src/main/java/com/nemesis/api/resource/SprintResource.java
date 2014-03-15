package com.nemesis.api.resource;

import javax.ws.rs.core.Response;

import com.nemesis.api.data.sprint.SprintData;

public interface SprintResource {

	public Response create(SprintData sprintData);

	public Response delete(String sprintId);

	public Response update(SprintData sprintData);
	
	public Response getAllSuites();
}

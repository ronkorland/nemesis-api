package com.nemesis.api.resource.impl;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nemesis.api.data.sprint.SprintData;
import com.nemesis.api.data.sprint.SprintsData;
import com.nemesis.api.resource.SprintResource;
import com.nemesis.api.service.SprintService;

@Path("/sprints")
@Component
@Produces({ MediaType.APPLICATION_JSON })
public class SprintResourceImpl implements SprintResource {

	@Autowired
	private SprintService sprintService;

	@Override
	@POST
	public Response create(SprintData sprintData) {
		SprintData returnDate = sprintService.create(sprintData);
		return Response.ok(returnDate).build();
	}

	@Override
	@DELETE
	@Path("/{sprintId}")
	public Response delete(@PathParam("sprintId") String sprintId) {
		SprintData sprintData = sprintService.delete(sprintId);
		return Response.ok(sprintData).build();
	}

	@Override
	@PUT
	public Response update(SprintData sprintData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET
	public Response getAllSuites() {
		SprintsData sprints = sprintService.getSprints();
		return Response.ok(sprints).build();
	}

}

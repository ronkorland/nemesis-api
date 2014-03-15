package com.nemesis.api.service;

import com.nemesis.api.data.sprint.SprintData;
import com.nemesis.api.data.sprint.SprintsData;

public interface SprintService {

	public SprintData create(SprintData sprintData);

	public SprintData findById(String sprintId);

	public SprintData delete(String sprintId);

	public SprintData update(SprintData sprintData);

	public SprintsData getSprints();

}

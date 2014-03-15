package com.nemesis.api.repository;

import java.util.List;

import com.nemesis.api.model.Sprint;

public interface SprintRepository {

	public List<Sprint> findAllSprints();

	public Sprint create(Sprint sprint);

	public Sprint delete(Sprint sprint);

	public Sprint findById(String sprintId);

	public Sprint save(Sprint sprint);
}

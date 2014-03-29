package com.nemesis.api.repository.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.nemesis.api.model.Sprint;
import com.nemesis.api.repository.SprintRepository;

@Repository
@Scope("singleton")
public class SprintRepositoryImpl extends RepositoryImpl<Sprint, String>
		implements SprintRepository {

	public SprintRepositoryImpl() {
		super(Sprint.class);
	}

	public SprintRepositoryImpl(Class<Sprint> entityClass) {
		super(Sprint.class);
	}

}

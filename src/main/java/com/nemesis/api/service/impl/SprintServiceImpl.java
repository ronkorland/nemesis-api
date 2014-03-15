package com.nemesis.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nemesis.api.data.sprint.SprintData;
import com.nemesis.api.data.sprint.SprintsData;
import com.nemesis.api.model.Sprint;
import com.nemesis.api.repository.SprintRepository;
import com.nemesis.api.service.SprintService;

@Service("sprintService")
@Scope("singleton")
public class SprintServiceImpl implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;

	@Override
	public SprintData create(SprintData sprintData) {
		Sprint sprint = new Sprint(sprintData);
		sprintRepository.create(sprint);
		return sprintData;
	}

	@Override
	public SprintData findById(String sprintId) {
		Sprint sprint = sprintRepository.findById(sprintId);
		SprintData data = new SprintData(sprint);
		return data;
	}

	@Override
	public SprintData delete(String sprintId) {
		SprintData sprintData = findById(sprintId);
		Sprint sprint = new Sprint(sprintData);
		sprintRepository.delete(sprint);
		return sprintData;
	}

	@Override
	public SprintData update(SprintData sprintData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SprintsData getSprints() {
		List<Sprint> sprints = sprintRepository.findAllSprints();
		List<SprintData> dataList = new ArrayList<SprintData>();
		if (sprints != null && sprints.size() > 0) {
			for (Sprint sprint : sprints) {
				dataList.add(new SprintData(sprint));
			}
		}

		SprintsData sprintsData = new SprintsData();
		sprintsData.setSprints(dataList);
		sprintsData.setTotal(dataList.size());
		return sprintsData;
	}

}

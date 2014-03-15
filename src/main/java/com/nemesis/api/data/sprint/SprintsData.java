package com.nemesis.api.data.sprint;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class SprintsData {

	@JsonDeserialize(as = ArrayList.class, contentAs = SprintData.class)
	private List<SprintData> sprints;

	private long total;

	private long totalPages;

	public List<SprintData> getSprints() {
		return sprints;
	}

	public void setSprints(List<SprintData> sprints) {
		this.sprints = sprints;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

}

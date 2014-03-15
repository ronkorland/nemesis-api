package com.nemesis.api.model;

import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.nemesis.api.data.sprint.SprintData;

@Document(collection = "sprints")
public class Sprint extends BaseModel {

	private String name;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate endDate;

	public Sprint() {
		super();
	}

	public Sprint(SprintData data) {
		super(data);
		setName(data.getName());
		setStartDate(data.getStartDate());
		setEndDate(data.getEndDate());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}

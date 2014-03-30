package com.nemesis.api.model;

import java.util.NoSuchElementException;

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

	public Sprint merge(Sprint other) {
		if (!this.getId().equals(other.getId())) {
			throw new NoSuchElementException("User object doesn't match");
		}
		if (this.getName() == null || !this.getName().equals(other.getName())) {
			this.setName(other.getName());
		}

		if (this.getStartDate() == null
				|| !this.getStartDate().isEqual(other.getStartDate())) {
			this.setStartDate(other.getStartDate());
		}

		if (this.getEndDate() == null
				|| !this.getEndDate().isEqual(other.getEndDate())) {
			this.setEndDate(other.getEndDate());
		}
		return this;
	}
}

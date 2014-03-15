package com.nemesis.api.data.sprint;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.LocalDate;

import com.nemesis.api.data.BaseData;
import com.nemesis.api.model.Sprint;
import com.nemesis.api.type.JodaDateDeserializer;
import com.nemesis.api.type.JodaDateSerializer;

public class SprintData extends BaseData {

	private String name;

	@JsonSerialize(using = JodaDateSerializer.class)
	@JsonDeserialize(using = JodaDateDeserializer.class)
	private LocalDate startDate;

	@JsonSerialize(using = JodaDateSerializer.class)
	@JsonDeserialize(using = JodaDateDeserializer.class)
	private LocalDate endDate;

	public SprintData() {
		super();
	}

	public SprintData(Sprint sprint) {
		super(sprint);
		setName(sprint.getName());
		setStartDate(sprint.getStartDate());
		setEndDate(sprint.getEndDate());
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

package com.nemesis.api.data.test;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.LocalDateTime;

import com.nemesis.api.data.BaseData;
import com.nemesis.api.model.TestPlan;
import com.nemesis.api.type.JodaDateTimeDeserializer;
import com.nemesis.api.type.JodaDateTimeSerializer;

public class TestPlanData extends BaseData {

	private String name;

	private String content;

	private String owner;

	@JsonSerialize(using = JodaDateTimeSerializer.class)
	@JsonDeserialize(using = JodaDateTimeDeserializer.class)
	private LocalDateTime creationDateTime;

	@JsonSerialize(using = JodaDateTimeSerializer.class)
	@JsonDeserialize(using = JodaDateTimeDeserializer.class)
	private LocalDateTime updateDateTime;

	private List<String> testIds;

	private List<String> suiteIds;

	public TestPlanData() {
		super();
	}

	public TestPlanData(TestPlan model) {
		super(model);
		setName(model.getName());
		setContent(model.getContent());
		setOwner(model.getOwner());
		setCreationDateTime(model.getCreationDateTime());
		setUpdateDateTime(model.getUpdateDateTime());
		setTestIds(model.getTestIds());
		setSuiteIds(model.getSuiteIds());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public List<String> getTestIds() {
		return testIds;
	}

	public void setTestIds(List<String> testIds) {
		this.testIds = testIds;
	}

	public List<String> getSuiteIds() {
		return suiteIds;
	}

	public void setSuiteIds(List<String> suiteIds) {
		this.suiteIds = suiteIds;
	}
}

package com.nemesis.api.model;

import java.util.List;
import java.util.NoSuchElementException;

import org.joda.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nemesis.api.data.test.TestPlanData;

@Document(collection = "testPlans")
public class TestPlan extends BaseModel {

	private String name;

	private String content;

	private String owner;

	private LocalDateTime creationDateTime;

	private LocalDateTime updateDateTime;

	private List<String> testIds;

	private List<String> suiteIds;

	public TestPlan() {
		super();
	}

	public TestPlan(TestPlanData data) {
		super(data);
		setName(data.getName());
		setContent(data.getContent());
		setOwner(data.getOwner());
		setCreationDateTime(data.getCreationDateTime());
		setUpdateDateTime(data.getUpdateDateTime());
		setTestIds(data.getTestIds());
		setSuiteIds(data.getSuiteIds());
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
		if (creationDateTime != null) {
			this.creationDateTime = creationDateTime;
		} else if (this.creationDateTime == null) {
			this.creationDateTime = LocalDateTime.now();
		}

	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		if (updateDateTime != null) {
			this.updateDateTime = updateDateTime;
		} else {
			this.updateDateTime = LocalDateTime.now();
		}

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

	public TestPlan merge(TestPlan other) {
		if (!this.getId().equals(other.getId())) {
			throw new NoSuchElementException("Test plan object doesn't match");
		}
		if (this.getContent() == null
				|| !this.getContent().equals(other.getContent())) {
			this.setContent(other.getContent());
		}
		if (this.getName() == null || !this.getName().equals(other.getName())) {
			this.setName(other.getName());
		}
		if (this.getOwner() == null
				|| !this.getOwner().equals(other.getOwner())) {
			this.setOwner(other.getOwner());
		}
		return this;
	}
}

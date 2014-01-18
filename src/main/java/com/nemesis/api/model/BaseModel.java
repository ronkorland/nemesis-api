package com.nemesis.api.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

import com.nemesis.api.data.BaseData;

public class BaseModel {

	@Id
	private String id;

	public BaseModel() {
		super();
		setId(UUID.randomUUID().toString());
	}

	public BaseModel(BaseData data) {
		this();
		setId(data.getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (StringUtils.hasText(id)) {
			this.id = id;
		}
	}
}

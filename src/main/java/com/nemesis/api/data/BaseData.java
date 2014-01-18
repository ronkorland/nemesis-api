package com.nemesis.api.data;

import java.util.UUID;

import org.springframework.util.StringUtils;

import com.nemesis.api.model.BaseModel;

public abstract class BaseData {

	private String id;

	public BaseData() {
		setId(UUID.randomUUID().toString());
	}

	public BaseData(BaseModel model) {
		this();
		setId(model.getId());

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

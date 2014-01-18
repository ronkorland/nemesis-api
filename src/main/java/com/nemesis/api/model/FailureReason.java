package com.nemesis.api.model;

import com.nemesis.api.data.FailureReasonData;

public class FailureReason {

	private String value;
	private String message;
	private String type;

	public FailureReason(){
		super();
	}
	
	public FailureReason(FailureReasonData data) {
		setMessage(data.getMessage());
		setValue(data.getValue());
		setType(data.getType());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

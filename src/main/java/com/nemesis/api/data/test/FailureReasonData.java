package com.nemesis.api.data.test;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.nemesis.api.model.FailureReason;

public class FailureReasonData {

	private String value;
	private String message;
	private String type;

	public FailureReasonData() {
		super();
	}

	public FailureReasonData(FailureReason model) {
		setMessage(model.getMessage());
		setValue(model.getValue());
		setType(model.getType());
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

	public static FailureReasonData buildFailureReason(Throwable throwable) {
		FailureReasonData failureOrError = new FailureReasonData();
		if (throwable.getMessage() != null) {
			failureOrError.setMessage(throwable.getMessage());
		}
		failureOrError.setType(throwable.getClass().toString());
		failureOrError.setValue(getStackTrace(throwable));

		return failureOrError;
	}

	private static String getStackTrace(Throwable t) {
		if (t != null) {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			t.printStackTrace(printWriter);
			return stringWriter.toString();
		}
		return "";
	}

}

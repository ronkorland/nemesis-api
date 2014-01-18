package com.nemesis.api.data;

import java.io.InputStream;

public class TestAttachmentData {

	private String testId;

	private InputStream file;

	private String fileName;

	public TestAttachmentData(String testId, InputStream file, String fileName) {
		this.file = file;
		this.testId = testId;
		this.fileName = fileName;
	}

	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}
}

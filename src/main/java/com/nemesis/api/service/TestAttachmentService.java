package com.nemesis.api.service;

import com.nemesis.api.data.test.TestAttachmentData;

public interface TestAttachmentService {

	public void deleteAttachment(String attachId);

	public void addAttachment(TestAttachmentData testAttachment);

	public String saveToFileById(String attachId, String folder);
}

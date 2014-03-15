package com.nemesis.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.nemesis.api.data.test.TestAttachmentData;
import com.nemesis.api.repository.StorageRepository;
import com.nemesis.api.repository.TestRepository;
import com.nemesis.api.service.TestAttachmentService;

@Service("testAttachmentService")
@Scope("singleton")
public class TestAttachmentServiceImpl implements TestAttachmentService {

	@Autowired
	StorageRepository storageRepository;

	@Autowired
	TestRepository testRepository;

	@Override
	public void deleteAttachment(String attachId) {
		storageRepository.deleteById(attachId);
		testRepository.deleteRefTestAttach(attachId);

	}

	@Override
	public void addAttachment(TestAttachmentData testAttachment) {
		String refId = storageRepository.save(testAttachment.getFile(), null,
				testAttachment.getFileName());

		testRepository.addRefToTestAttach(testAttachment.getTestId(), refId);
	}

	@Override
	public String saveToFileById(String attachId, String folder) {
		try {
			return storageRepository.saveToFileById(attachId, folder);
		} catch (Exception e) {
			return null;
		}
	}

}

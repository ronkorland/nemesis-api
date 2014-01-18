package com.nemesis.api.repository.impl;

import java.io.File;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.nemesis.api.repository.StorageRepository;

@Repository
@Scope("singleton")
public class StorageRepositoryImpl implements StorageRepository {

	@Autowired
	GridFsTemplate gridFsTemplate;

	@Override
	public String save(InputStream inputStream, String contentType,
			String filename) {

		DBObject metaData = new BasicDBObject();
		metaData.put("meta1", filename);
		metaData.put("meta2", contentType);

		GridFSFile file = gridFsTemplate.store(inputStream, filename, metaData);

		return file.getId().toString();
	}

	@Override
	public GridFSDBFile get(String id) {
		System.out.println("Finding by ID: " + id);
		return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(
				new ObjectId(id))));
	}

	@Override
	public GridFSDBFile getByFilename(String filename) {
		return gridFsTemplate.findOne(new Query(Criteria.where("filename").is(
				filename)));
	}

	@Override
	public void deleteById(String attachId) {
		gridFsTemplate.delete(new Query(Criteria.where("_id").is(
				new ObjectId(attachId))));
	}

	@Override
	public String saveToFileById(String attachId, String folder)
			throws Exception {
		GridFSDBFile gridFSDBFile = get(attachId);
		Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String uploadDate = formatter.format(gridFSDBFile.getUploadDate());
		// TODO change tmp folder to http folder
		String fileName = uploadDate + gridFSDBFile.getFilename();
		String uploadedFileLocation = folder + fileName;

		File f = new File(uploadedFileLocation);
		if (!f.exists()) {
			gridFSDBFile.writeTo(uploadedFileLocation);
		}
		return fileName;
	}

}

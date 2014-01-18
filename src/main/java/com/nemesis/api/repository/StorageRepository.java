package com.nemesis.api.repository;

import java.io.InputStream;

import com.mongodb.gridfs.GridFSDBFile;

public interface StorageRepository {

	public String save(InputStream inputStream, String contentType, String filename);

	public GridFSDBFile get(String id);

	public GridFSDBFile getByFilename(String filename);

	public void deleteById(String attachId);

	public String saveToFileById(String attachId, String folder) throws Exception;

}

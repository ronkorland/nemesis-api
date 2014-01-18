package com.nemesis.api.resource;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

public interface TestAttachmentResource {

	public Response addAttach(@PathParam("testId") String testId,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail);

	public Response deleteAttach(@PathParam("attachId") String attachId);

	public Response saveToFileById(@PathParam("attachId") String attachId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) throws Exception;

}

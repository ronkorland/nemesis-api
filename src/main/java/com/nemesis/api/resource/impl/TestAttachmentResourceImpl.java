package com.nemesis.api.resource.impl;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nemesis.api.configuration.WebReportConfiguration;
import com.nemesis.api.data.test.TestAttachmentData;
import com.nemesis.api.resource.TestAttachmentResource;
import com.nemesis.api.service.TestAttachmentService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/attach")
@Component
@Produces({ MediaType.APPLICATION_JSON })
public class TestAttachmentResourceImpl implements TestAttachmentResource {

	@Autowired
	private TestAttachmentService testAttachmentService;

	@Autowired
	private WebReportConfiguration webReportConfiguration;

	private String fileLocalFolder = "C:\\tomcat_files\\";

	private String fileUrlPrefix = "http://localhost/files/";

	@POST
	@Path("/{testId}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addAttach(@PathParam("testId") String testId,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		TestAttachmentData testAttachment = new TestAttachmentData(testId,
				uploadedInputStream, fileDetail.getFileName());
		testAttachmentService.addAttachment(testAttachment);
		return Response.ok("{\"status\":\"OK\"}").build();
	}

	@DELETE
	@Path("/{attachId}")
	public Response deleteAttach(@PathParam("attachId") String attachId) {
		testAttachmentService.deleteAttachment(attachId);
		return Response.ok("{\"status\":\"OK\"}").build();
	}

	@GET
	@Path("/{attachId}")
	public Response saveToFileById(@PathParam("attachId") String attachId,
			@Context final HttpServletRequest request,
			@Context final HttpServletResponse response) throws Exception {

		// String url = "http://" + uriInfo.getBaseUri().getHost();
		// + ":" + port;
		// String tempFolder = "C:\\tomcat_files\\";

		String fileName = testAttachmentService.saveToFileById(attachId,
				fileLocalFolder);

		if (fileName == null) {
			Response.status(Status.CONFLICT).build();
		} else {
			String fullUrl = fileUrlPrefix + fileName;
			response.sendRedirect(fullUrl);
			return Response.ok("{\"url\":\"" + fullUrl + "\"}").build();
		}
		return Response.status(Status.CONFLICT).build();
	}
}

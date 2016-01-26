package net.darkowl.darkNet.darkWatch.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AbsDarkNetController {

	protected <T> Response formatOutput(T resource,
			List<MediaType> acceptableMediaTypes) {
		for (final MediaType item : acceptableMediaTypes) {
			System.out.println(item.toString());
		}

		return Response.status(Response.Status.OK).entity(resource.toString())
				.build();
	}

	protected <T> Response returnError(Exception e) {

		return Response.status(Response.Status.BAD_REQUEST)
				.entity(e.toString()).build();
	}
}

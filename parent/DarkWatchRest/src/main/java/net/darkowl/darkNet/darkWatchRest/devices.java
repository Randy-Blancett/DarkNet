/**
 * 
 */
package net.darkowl.darkNet.darkWatchRest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * @author Randy Blancett
 * @since Jan 14, 2016
 * 
 */
@Path("/devices")
public class devices {
	@GET
	public Response getMsg(@PathParam("param") String msg) {

		String output = "Jersey say : " + msg;

		return Response.status(200).entity(output).build();

	}
}

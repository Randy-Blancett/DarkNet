package net.darkowl.darkNet.darkWatch.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class TestController extends AbsDarkNetController {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		return "Test Get";
	}

}

package com.github.rwsargent.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {
	
	@GET
	public String getView() {
		return "<h2>Hello!</h2>";
	}
}

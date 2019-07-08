package com.github.rwsargent.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.rwsargent.views.AutograderView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeRoute {
	
	@GET
	public AutograderView getView() {
	    return new AutograderView();
	}
}

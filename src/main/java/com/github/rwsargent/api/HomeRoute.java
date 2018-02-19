package com.github.rwsargent.api;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeRoute {
	
	@GET
	public InputStream getView() {
//		MustacheFactory mf = new DefaultMustacheFactory();
//	    Mustache mustache = mf.compile("example.mustache");
//	    StringWriter stringWriter = new StringWriter();
//	    try {
//			mustache.execute(stringWriter, new AutograderView()).flush();
//		} catch (IOException e) {
//			
//		}
	    return getClass().getClassLoader().getResourceAsStream("com/github/rwsargent/views/example.mustache");
	}
}

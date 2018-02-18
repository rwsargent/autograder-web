package com.github.rwsargent;

import com.github.rwsargent.api.AutoGraderResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AutograderWebApplication extends Application<AutograderWebConfiguration>{

	@Override
	public void run(AutograderWebConfiguration config, Environment env) throws Exception {
		AutoGraderResource resource = new AutoGraderResource(config.getTemplate(), config.getDefaultName());
		env.jersey().register(resource);
	}
	
	@Override
	public String getName() {
		return "autograder-web";
	}
	
	@Override
	public void initialize(Bootstrap<AutograderWebConfiguration> bootstrap) {
	}
}

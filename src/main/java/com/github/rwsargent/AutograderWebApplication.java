package com.github.rwsargent;

import com.github.rwsargent.modules.AutograderWebModule;
import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class AutograderWebApplication extends Application<AutograderWebConfiguration>{
	
	private GuiceBundle<AutograderWebConfiguration> guiceBundle;


	@Override
	public void run(AutograderWebConfiguration config, Environment environment) throws Exception {
//		AutoGraderResource resource = new AutoGraderResource(config.getTemplate(), config.getDefaultName());
//		environment.jersey().register(resource);
	}
	
	@Override
	public String getName() {
		return "autograder-web";
	}
	
	@Override
	public void initialize(Bootstrap<AutograderWebConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/static", "/public"));
		
		guiceBundle = GuiceBundle.<AutograderWebConfiguration>newBuilder()
	      .addModule(new AutograderWebModule())
	      .setConfigClass(AutograderWebConfiguration.class)
	      .enableAutoConfig(getClass().getPackage().getName())
	      .build();
		
		bootstrap.addBundle(guiceBundle);
		bootstrap.addBundle(new ViewBundle<AutograderWebConfiguration>());
		
	}
	
	public static void main(String[] args) throws Exception {
		new AutograderWebApplication().run(args);
	}
}

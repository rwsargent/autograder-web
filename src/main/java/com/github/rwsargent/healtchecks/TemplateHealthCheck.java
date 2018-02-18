package com.github.rwsargent.healtchecks;

import com.hubspot.dropwizard.guice.InjectableHealthCheck;

public class TemplateHealthCheck extends InjectableHealthCheck {
	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

	@Override
	public String getName() {
		return "Shut-up health check";
	}

}

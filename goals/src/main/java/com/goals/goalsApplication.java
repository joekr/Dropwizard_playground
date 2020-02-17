package com.goals;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class goalsApplication extends Application<goalsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new goalsApplication().run(args);
    }

    @Override
    public String getName() {
        return "goals";
    }

    @Override
    public void initialize(final Bootstrap<goalsConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final goalsConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

package com.temperature;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class temperatureApplication extends Application<temperatureConfiguration> {

    public static void main(final String[] args) throws Exception {
        new temperatureApplication().run(args);
    }

    @Override
    public String getName() {
        return "temperature";
    }

    @Override
    public void initialize(final Bootstrap<temperatureConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final temperatureConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

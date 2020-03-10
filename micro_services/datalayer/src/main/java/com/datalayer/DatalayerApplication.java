package com.datalayer;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.HttpConfiguration;

public class DatalayerApplication extends Application<DatalayerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DatalayerApplication().run(args);
    }

    @Override
    public String getName() {
        return "datalayer-project";
    }

    @Override
    public void initialize(final Bootstrap<DatalayerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DatalayerConfiguration configuration,
                    final Environment environment) {
    }

}

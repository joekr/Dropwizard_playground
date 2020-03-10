package com.ident;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.HttpConfiguration;

public class IdentityApplication extends Application<IdentityConfiguration> {

    public static void main(final String[] args) throws Exception {
        new IdentityApplication().run(args);
    }

    @Override
    public String getName() {
        return "identity-project";
    }

    @Override
    public void initialize(final Bootstrap<IdentityConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final IdentityConfiguration configuration,
                    final Environment environment) {
    }

}

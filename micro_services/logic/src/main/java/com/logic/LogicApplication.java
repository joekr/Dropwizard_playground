package com.logic;

import com.logic.db.UserDao;
import com.logic.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.HttpConfiguration;

public class LogicApplication extends Application<LogicConfiguration> {

    public static void main(final String[] args) throws Exception {
        new LogicApplication().run(args);
    }

    @Override
    public String getName() {
        return "logic-project";
    }

    @Override
    public void initialize(final Bootstrap<LogicConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final LogicConfiguration configuration,
                    final Environment environment) {

//        HttpConfiguration config = new HttpConfiguration();
//        config.setPort(8085);
//        config.setAdminPort(8086);
//        configuration.setHttpConfiguration(config);

//        TODO: setup guice
        final UserDao userDao = new UserDao();
        final UserResource userResource = new UserResource(userDao);

        environment.jersey().register(userResource);
    }

}

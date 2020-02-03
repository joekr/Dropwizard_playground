package com.helloworld;

import com.helloworld.health.TemplateHealthCheck;
import com.helloworld.resource.HelloWorldResource;
import com.helloworld.core.Person;
import com.helloworld.db.PersonDao;

import org.jdbi.v3.core.Jdbi;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(final Bootstrap<HelloWorldConfiguration> bootstrap) {

    }

    @Override
    public void run(final HelloWorldConfiguration configuration,
                    final Environment environment) {

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final PersonDao personDao = jdbi.onDemand(PersonDao.class);

        final HelloWorldResource resource = new HelloWorldResource(
            configuration.getTemplate(),
            configuration.getDefaultName(),
            personDao
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

        environment.jersey().register(resource);
        environment.healthChecks().register("template", healthCheck);
    }

}

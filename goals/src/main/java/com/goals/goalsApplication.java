package com.goals;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;
import io.dropwizard.migrations.MigrationsBundle;

public class GoalsApplication extends Application<GoalsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new GoalsApplication().run(args);
    }

    @Override
    public String getName() {
        return "goals";
    }

    @Override
    public void initialize(final Bootstrap<GoalsConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<GoalsConfiguration>(){
            @Override
            public DataSourceFactory getDataSourceFactory(GoalsConfiguration config){
                return config.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final GoalsConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

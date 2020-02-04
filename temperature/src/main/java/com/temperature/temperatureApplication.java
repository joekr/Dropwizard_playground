package com.temperature;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TemperatureApplication extends Application<TemperatureConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TemperatureApplication().run(args);
    }

    @Override
    public String getName() {
        return "temperature";
    }

    @Override
    public void initialize(final Bootstrap<TemperatureConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<TemperatureConfiguration>(){
            @Override
            public DataSourceFactory getDataSourceFactory(TemperatureConfiguration config){
                return config.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final TemperatureConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

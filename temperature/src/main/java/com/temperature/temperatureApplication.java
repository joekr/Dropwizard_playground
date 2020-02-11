package com.temperature;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.temperature.db.ReadingDao;
import com.temperature.db.RoomDao;
import com.temperature.db.SensorDao;
import com.temperature.resources.RoomResource;
import com.temperature.resources.SensorResource;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

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

        bootstrap.addBundle(new ViewBundle<TemperatureConfiguration>() );
    }

    @Override
    public void run(final TemperatureConfiguration configuration, final Environment environment) {

        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        final RoomDao roomDao = jdbi.onDemand(RoomDao.class);
        final SensorDao sensorDao = jdbi.onDemand(SensorDao.class);
        final ReadingDao readingDao = jdbi.onDemand(ReadingDao.class);

        // Injector roomInjector = Guice.createInjector(new RoomModule());
        // environment.jersey().register(roomResource);


        final RoomResource roomResource = new RoomResource(roomDao, sensorDao);
        final SensorResource sensorResource = new SensorResource(sensorDao, readingDao);

        environment.jersey().register(roomResource);
        environment.jersey().register(sensorResource);
    }

}

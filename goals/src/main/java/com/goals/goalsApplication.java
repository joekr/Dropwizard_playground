package com.goals;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import ch.qos.logback.core.db.DataSourceConnectionSource;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;

import com.goals.auth.GoalsAuthenticator;
import com.goals.core.User;
import com.goals.db.UserDao;
import com.goals.resources.GoalResource;
import com.goals.resources.UserResource;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;
import io.dropwizard.migrations.MigrationsBundle;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;

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


        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        final UserDao userDao = jdbi.onDemand(UserDao.class);

        final GoalResource goalResource = new GoalResource();
        final UserResource userResource = new UserResource(userDao);

        environment.jersey().register(goalResource);
        environment.jersey().register(userResource);

        environment.jersey().register(new AuthDynamicFeature(
            new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new GoalsAuthenticator(userDao))
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

}

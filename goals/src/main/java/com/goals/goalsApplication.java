package com.goals;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
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
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

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
        final UserResource userResource = new UserResource(userDao, configuration.getJWTSecret());

        environment.jersey().register(goalResource);
        environment.jersey().register(userResource);

        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(configuration.getJWTSecret().getBytes())) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance

        environment.jersey().register(new AuthDynamicFeature(
            new JwtAuthFilter.Builder<User>()
                    .setJwtConsumer(consumer)
                    .setAuthenticator(new GoalsAuthenticator(userDao))
                    .setRealm("realm")
                    .setPrefix("Bearer")
                    .buildAuthFilter()));
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

}

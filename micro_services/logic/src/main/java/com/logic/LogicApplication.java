package com.logic;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.logic.api.User;
import com.logic.auth.LogicAuthenticator;
import com.logic.db.UserDao;
import com.logic.resources.TestResource;
import com.logic.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.HttpConfiguration;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

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

         Injector logicInjector = Guice.createInjector(new LogicModule());
         environment.jersey().register(logicInjector);

        final UserDao userDao = logicInjector.getInstance(UserDao.class);
        final UserResource userResource = new UserResource(userDao);
        final TestResource testResource = new TestResource();

        environment.jersey().register(userResource);
        environment.jersey().register(testResource);

        environment.jersey().register(new AuthDynamicFeature(
            new OAuthCredentialAuthFilter.Builder<User>()
                    .setAuthenticator(new LogicAuthenticator(userDao))
                    .setPrefix("Bearer")
                    .buildAuthFilter()));
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

}

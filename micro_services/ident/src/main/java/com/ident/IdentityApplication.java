package com.ident;

import com.ident.db.UserDao;
import com.ident.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.HttpConfiguration;
import org.jdbi.v3.core.Jdbi;

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
        bootstrap.addBundle(new MigrationsBundle<IdentityConfiguration>(){
            @Override
            public DataSourceFactory getDataSourceFactory(IdentityConfiguration config){
                return config.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final IdentityConfiguration configuration,
                    final Environment environment) {


        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        final UserDao userDao = jdbi.onDemand(UserDao.class);
        final UserResource userResource = new UserResource(userDao, configuration.getJWTSecret());

        environment.jersey().register(userResource);
    }

}

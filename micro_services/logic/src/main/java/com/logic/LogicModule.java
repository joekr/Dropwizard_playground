package com.logic;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.logic.resources.UserResource;
import io.dropwizard.Configuration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class LogicModule extends AbstractModule {
    private LogicConfiguration conf;

    public LogicModule(LogicConfiguration conf) {
        this.conf = conf;
    }

    @Override
    protected void configure() {
        bind(UserResource.class);
        bind(LogicConfiguration.class).toInstance(conf);
    }

    @Provides
    @Singleton
    Client getClient() {
        return ClientBuilder.newClient();
    }

    @Provides
    @Singleton
    public String getIdentHost() {
        return this.conf.getIdentHost();
    }
}

package com.logic;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.logic.resources.UserResource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class LogicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserResource.class);
    }

    @Provides
    @Singleton
    Client getClient() {
        return ClientBuilder.newClient();
    }
}

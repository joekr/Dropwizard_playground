package com.logic;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class LogicModule extends AbstractModule {

    @Provides
    @Singleton
    Client getClient() {
        return ClientBuilder.newClient();
    }
}

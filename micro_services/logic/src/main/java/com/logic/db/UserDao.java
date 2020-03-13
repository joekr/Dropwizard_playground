package com.logic.db;

import com.google.inject.Inject;
import com.logic.api.UserSignin;
import io.dropwizard.client.HttpClientConfiguration;
import org.glassfish.jersey.client.JerseyClient;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class UserDao {
    private final Client client;

    @Inject
    public UserDao(Client client) {
        this.client = client;
    }

    public Optional<String> auth(UserSignin user) {
//        final Client client = ClientBuilder.newClient();
//        TODO: move out of class using GUICE and conf
        WebTarget webTarget = this.client.target("http://localhost:9004/users/signin");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            @SuppressWarnings("rawtypes")
            String jwt = response.readEntity(String.class);

            return Optional.of(jwt);
        } else {
            return Optional.empty();
        }
    }
}

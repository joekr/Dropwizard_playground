package com.logic.db;

import com.google.inject.Inject;
import com.logic.api.User;
import com.logic.api.UserSignin;
import io.dropwizard.client.HttpClientConfiguration;
import org.glassfish.jersey.client.JerseyClient;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class UserDao {
    private final Client client;
    private final String identHost;

    @Inject
    public UserDao(Client client, String identHost) {

        this.client = client;
        this.identHost = identHost;

    }

    public Optional<User> auth(String jwt) {
        final String target = String.format("%s/users/authenticate", this.identHost);

        WebTarget webTarget = this.client.target(target);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(jwt, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            @SuppressWarnings("rawtypes")
            User user = response.readEntity(User.class);

            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public Optional<String> signin(UserSignin user) {
        final String target = String.format("%s/users/signin", this.identHost);

        WebTarget webTarget = this.client.target(target);
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

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

    @Inject
    public UserDao(Client client) {
        this.client = client;
    }

    public Optional<User> auth(String jwt) {
        WebTarget webTarget = this.client.target("http://localhost:9004/users/authenticate");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(jwt, MediaType.APPLICATION_JSON));

//        System.out.println(response);
//        System.out.println(response.readEntity(String.class));
        if (response.getStatus() == 200) {
            @SuppressWarnings("rawtypes")
            User user = response.readEntity(User.class);

            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public Optional<String> signin(UserSignin user) {
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

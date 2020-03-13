package com.logic.resources;

import com.logic.api.UserSignin;
import com.logic.db.UserDao;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDao userDao;

    public UserResource(UserDao userDao){
        this.userDao = userDao;
    }

    @POST
    @Path("/signin")
    public Response signIn(UserSignin user) {
        final Optional<String> jwt = this.userDao.signin(user);

        if (!jwt.isPresent()) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.status(Response.Status.OK).entity(jwt).build();
    }
}

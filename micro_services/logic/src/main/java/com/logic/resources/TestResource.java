package com.logic.resources;

import com.logic.api.User;
import com.logic.api.UserSignin;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/tests")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    @GET
    @PermitAll
    public Response test(@Auth User user) {
        return Response.status(Response.Status.OK).entity("TESTING").build();
    }
}

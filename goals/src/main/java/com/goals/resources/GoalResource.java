package com.goals.resources;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.annotation.security.PermitAll;

import io.dropwizard.auth.Auth;
import com.goals.core.User;;

// Dummy class for now to test auth
@Path("/goals")
@Produces(MediaType.APPLICATION_JSON)
public class GoalResource {

  @GET
  @PermitAll
  public Response listGoals(@Auth User user) {
    final String message = String.format("{\"message\": \"%s\"}", user.getName());
    return Response.status(Status.OK).entity(message).build();
  }
}

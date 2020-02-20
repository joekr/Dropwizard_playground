package com.goals.resources;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.annotation.security.PermitAll;

import io.dropwizard.auth.Auth;

import com.goals.api.UserSignup;
import com.goals.core.User;
import com.goals.db.UserDao;

import org.mindrot.jbcrypt.BCrypt;

// Dummy class for now to test auth
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
  private final UserDao userDao;

  public UserResource(UserDao userDao){
    this.userDao = userDao;
  }

  @POST
  @Path("/signup")
  public Response listGoals(UserSignup user) {
    String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
    this.userDao.create(user.getUsername(), hashed);
    return Response.status(Status.CREATED).entity(message).build();
  }
}

package com.goals.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.annotation.security.PermitAll;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.goals.core.TeamDo;
import com.goals.db.TeamDao;
import io.dropwizard.auth.Auth;

import com.goals.api.UserSignup;
import com.goals.core.User;
import com.goals.core.UserDo;
import com.goals.db.UserDao;

import org.mindrot.jbcrypt.BCrypt;
import sun.misc.ObjectInputFilter;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


// Dummy class for now to test auth
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
  private final UserDao userDao;
  private final TeamDao teamDao;
  private final String jwtSecret;

  public UserResource(UserDao userDao, TeamDao teamDao, String jwtSecret){
    this.userDao = userDao;
    this.teamDao = teamDao;
    this.jwtSecret = jwtSecret;
  }

  @POST
  @Path("/signup")
  public Response listGoals(UserSignup user) {
    String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
    final Optional<TeamDo> teamDo = this.teamDao.create(user.getTeamName());

    if (!teamDo.isPresent()) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }

    final String token = UUID.randomUUID().toString();
    final Optional<UserDo> userDo = this.userDao.create(user.getUsername().toLowerCase(), hashed, teamDo.get().getId(), token);

    if (userDo.isPresent()) {
      return Response.status(Status.CREATED).entity(userDo).build();
    } else {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @POST
  @Path("/signin")
  public Response signIn(UserSignup user) {
    final Optional<UserDo> userDoOp = this.userDao.findByEmail(user.getUsername().toLowerCase());

    if (!userDoOp.isPresent()) {
      return Response.status(Status.UNAUTHORIZED).build();
    }

    final UserDo userDo = userDoOp.get();

    if (!BCrypt.checkpw(user.getPassword(), userDo.getPasswordHash())) {
      return Response.status(Status.UNAUTHORIZED).build();
    }

    Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(Calendar.WEEK_OF_MONTH, 2);
    date = c.getTime();

    String token = null;
    try {
      Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret);
      token = JWT.create()
              .withClaim("name", userDo.getEmail())
              .withClaim("id", userDo.getId())
              .withSubject(userDo.getEmail())
              .withIssuer("auth")
              .withExpiresAt(date)
              .sign(algorithm);
    } catch (JWTCreationException exception){
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
//    TODO: save JWT to user
    this.userDao.updateLoggedin(userDo.getId());

    return Response.status(Status.OK).entity(token).build();
  }

  @POST
  @Path("/confirmation/{token}")
  public Response confirmation(@PathParam("token") String token) {
    final Optional<UserDo> userDoOp = this.userDao.findByConfermationToke(token);

    if (!userDoOp.isPresent()) {
      return Response.status(Status.UNAUTHORIZED).build();
    }

    final UserDo userDo = userDoOp.get();
    final int id = this.userDao.confirm(userDo.getId());

    if (id > 0) {
      return Response.status(Status.OK).build();
    } else {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

  @GET
  @PermitAll
  public Response getUser(@Auth User user) {
    final Optional<UserDo> userDo = this.userDao.findByEmail(user.getName().toLowerCase());

    if (userDo.isPresent()) {
      return Response.status(Status.OK).entity(userDo).build();
    } else {
      return Response.status(Status.NOT_FOUND).build();
    }
  }
}

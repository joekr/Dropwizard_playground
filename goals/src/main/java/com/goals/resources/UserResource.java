package com.goals.resources;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.annotation.security.PermitAll;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
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


// Dummy class for now to test auth
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
  private final UserDao userDao;
  private final String jwtSecret;

  public UserResource(UserDao userDao, String jwtSecret){
    this.userDao = userDao;
    this.jwtSecret = jwtSecret;
  }

  @POST
  @Path("/signup")
  public Response listGoals(UserSignup user) {
    String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
    final UserDo userDo = this.userDao.create(user.getUsername().toLowerCase(), hashed);
    return Response.status(Status.CREATED).entity(userDo).build();
  }

  @POST
  @Path("/signin")
  public Response signIn(UserSignup user) {
    final UserDo userDo = this.userDao.findByEmail(user.getUsername().toLowerCase());

    if (user == null || !BCrypt.checkpw(user.getPassword(), userDo.getPasswordHash())) {
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

    return Response.status(Status.OK).entity(token).build();
  }
}

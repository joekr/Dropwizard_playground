package com.goals.auth;

import java.util.Optional;

import com.goals.core.User;
import com.goals.core.UserDo;
import com.goals.db.UserDao;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.auth.AuthenticationException;

import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtContext;
import org.mindrot.jbcrypt.BCrypt;


public class GoalsAuthenticator implements Authenticator<JwtContext, User> {
  final private UserDao userDao;

  public GoalsAuthenticator(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public Optional<User> authenticate(JwtContext context) throws AuthenticationException {
    try {
      final String email = context.getJwtClaims().getSubject();
      return Optional.of(new User(email));
    } catch (MalformedClaimException e) { return Optional.empty(); }
  }
}

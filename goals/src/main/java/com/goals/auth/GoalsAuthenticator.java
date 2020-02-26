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
      final Optional<UserDo> userDo = this.userDao.findByEmail(email.toLowerCase());

      if (userDo.isPresent()) {
        return Optional.of(new User(userDo.get().getId(), userDo.get().getEmail(), userDo.get().getTeamId()));
      } else {
        return Optional.empty();
      }

    } catch (MalformedClaimException e) { return Optional.empty(); }
  }
}

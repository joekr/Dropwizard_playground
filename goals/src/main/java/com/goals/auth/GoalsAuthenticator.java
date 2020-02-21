package com.goals.auth;

import java.util.Optional;

import com.goals.core.User;
import com.goals.core.UserDo;
import com.goals.db.UserDao;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.auth.AuthenticationException;

import org.mindrot.jbcrypt.BCrypt;

public class GoalsAuthenticator implements Authenticator<BasicCredentials, User> {
  final private UserDao userDao;

  public GoalsAuthenticator(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
    final UserDo user = userDao.findByEmail(credentials.getUsername());

    if (user != null && BCrypt.checkpw(credentials.getPassword(), user.getPasswordHash())) {
        return Optional.of(new User(credentials.getUsername()));
    }

    return Optional.empty();
  }
}

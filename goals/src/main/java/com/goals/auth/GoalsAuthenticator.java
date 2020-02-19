package com.goals.auth;

import java.util.Optional;

import com.goals.core.User;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.auth.AuthenticationException;


public class GoalsAuthenticator implements Authenticator<BasicCredentials, User> {

  @Override
  public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
      if ("secret".equals(credentials.getPassword())) {
          return Optional.of(new User(credentials.getUsername()));
      }

      return Optional.empty();
  }
}

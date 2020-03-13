package com.logic.auth;

import com.logic.api.User;
import com.logic.db.UserDao;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtContext;

import java.util.Optional;

public class LogicAuthenticator implements Authenticator<String, User> {
    final private UserDao userDao;

    public LogicAuthenticator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> authenticate(String jwt) throws AuthenticationException {
//        try {
            final Optional<User> user = this.userDao.auth(jwt);

            return user;
//            if (user.isPresent()) {
//                return Optional.of(user);
//            } else {
//                return Optional.empty();
//            }

//        } catch (MalformedClaimException e) { return Optional.empty(); }
    }
}

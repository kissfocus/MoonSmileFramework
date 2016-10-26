package com.apptech.base.security;

import com.apptech.base.identity.AuthedUser;
import com.apptech.base.identity.User;
import com.google.common.base.Strings;
import com.google.inject.Singleton;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

@Singleton
public class FrameAuthenticator implements Authenticator<FrameCredentials, AuthedUser> {

    @Override
    public Optional<AuthedUser> authenticate(FrameCredentials credentials) throws AuthenticationException {
        String tokenString = credentials.getToken();
        String userId = FrameAuthResolver.verifyToken(tokenString);
        if (!Strings.isNullOrEmpty(userId)) {
            User user=new User();
            AuthedUser authUser = new AuthedUser(user,tokenString);
            return Optional.of(authUser);
        } else {
            return Optional.empty();
        }
    }
}

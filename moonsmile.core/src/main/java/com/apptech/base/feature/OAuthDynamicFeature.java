package com.apptech.base.feature;

import com.apptech.base.identity.AuthedUser;
import com.apptech.base.security.FrameAuthFilter;
import com.apptech.base.security.FrameAuthenticator;
import com.google.inject.Inject;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.PermitAllAuthorizer;
import io.dropwizard.setup.Environment;

import javax.ws.rs.ext.Provider;


@Provider
public class OAuthDynamicFeature extends AuthDynamicFeature {
    @Inject
    OAuthDynamicFeature(FrameAuthenticator authenticator, FrameAuthenticator authorizer, Environment environment) {
        super(new FrameAuthFilter.Builder()
                .setAuthenticator(authenticator)
                .setAuthorizer(new PermitAllAuthorizer<>())
                .setRealm("user")
                .buildAuthFilter());

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AuthedUser.class));
    }
}

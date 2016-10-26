package com.apptech.base.identity;

import java.security.Principal;

public class AuthedUser implements Principal {

    private final User user;
    private final String cookie;

    public AuthedUser(User user,
                      String cookie) {
        this.user = user;
        this.cookie = cookie;
    }

    public User getUser() {
        return user;
    }

    public String getCookie() {
        return cookie;
    }

    @Override
    public String getName() {
        return "Authed User";
    }
}

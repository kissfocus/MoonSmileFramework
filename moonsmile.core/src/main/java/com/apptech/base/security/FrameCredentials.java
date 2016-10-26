package com.apptech.base.security;

public class FrameCredentials {
    private final String token;

    public FrameCredentials(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() { return token; }
}

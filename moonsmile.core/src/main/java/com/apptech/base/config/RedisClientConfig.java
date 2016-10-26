package com.apptech.base.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.net.HostAndPort;
import redis.clients.jedis.Protocol;

import javax.validation.constraints.NotNull;
import java.net.URI;

public class RedisClientConfig {
    public static final int DEFAULT_PORT = 6379;
    public static final int DEFAULT_MAX_TOTAL = 1024;
    private static final String REDIS_SSL = "rediss";

    @JsonProperty
    @NotNull
    private HostAndPort endpoint;

    @JsonProperty
    private String password;

    @JsonProperty
    private int minIdle = 0;

    @JsonProperty
    private int maxIdle = 0;

    @JsonProperty
    private int maxTotal = DEFAULT_MAX_TOTAL;

    @JsonProperty
    private boolean ssl = false;

    @JsonProperty
    private int timeout = Protocol.DEFAULT_TIMEOUT;

    @JsonProperty
    public void setUrl(URI uri) {
        this.endpoint = HostAndPort.fromParts(uri.getHost(), uri.getPort());
        String userInfo = uri.getUserInfo();
        if (userInfo != null && !userInfo.isEmpty()) {
            String[] credentials = userInfo.split(":");
            this.password = credentials[1];
        }
        if (uri.getScheme().equals(REDIS_SSL)) {
            this.ssl = true;
        }
    }

    public HostAndPort getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(HostAndPort endpoint) {
        this.endpoint = endpoint;
    }

    public String getHost() {
        return endpoint.getHostText();
    }

    public int getPort() {
        return endpoint.getPortOrDefault(DEFAULT_PORT);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public boolean getSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}

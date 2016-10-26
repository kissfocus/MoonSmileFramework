package com.apptech.base;

import com.apptech.base.config.AppClientConfig;
import com.apptech.base.config.RedisClientConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class FrameConfiguration extends Configuration {

    @NotNull
    @JsonProperty
    private AppClientConfig clientconfig;

    @JsonProperty
    private RedisClientConfig redis;

    public AppClientConfig getClientConfig() {
        return clientconfig;
    }

    public RedisClientConfig getRedis() {
        return redis;
    }
}

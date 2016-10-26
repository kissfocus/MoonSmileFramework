package com.apptech.base.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class AppClientConfig {

    @NotNull
    @JsonProperty
    private String type;

    @NotNull
    @JsonProperty
    private String url;

    @JsonProperty
    private List<String> readlocal;

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isUsingLocalConfig() {
        return type.equals("file");
    }

    public List<String> getReadlocal() {
        return readlocal;
    }
}

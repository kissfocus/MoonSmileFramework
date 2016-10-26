package com.apptech.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class SystemSetting {

    @NotNull
    @JsonProperty
    private String endPointUrl;

    public String getEndPointUrl() {
        return endPointUrl;
    }
}

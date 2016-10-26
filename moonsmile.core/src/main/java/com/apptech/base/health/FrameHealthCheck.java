package com.apptech.base.health;

import ru.vyarus.dropwizard.guice.module.installer.feature.health.NamedHealthCheck;

public class FrameHealthCheck extends NamedHealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy("WebService is running");
    }

    @Override
    public String getName() {
        return "AppHealth";
    }
}

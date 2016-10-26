package com.apptech.base;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.joda.time.DateTimeZone;

import java.util.TimeZone;

public class FrameService<T extends FrameConfiguration> extends Application<T> {

    @Override
    public void initialize(Bootstrap<T> bootstrap) {
        TimeZone china = TimeZone.getTimeZone("GMT+08:00");
        DateTimeZone.setDefault(DateTimeZone.forTimeZone(china));
    }

    @Override
    public void run(T configuration, Environment environment) throws Exception {
        FrameBridge.init(configuration, environment);
    }

    public String scanPackageName() {
        return FrameService.class.getPackage().getName();
    }
}

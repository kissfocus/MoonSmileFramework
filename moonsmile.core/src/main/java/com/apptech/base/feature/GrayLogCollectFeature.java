package com.apptech.base.feature;

import com.google.inject.Inject;
import io.dropwizard.setup.Environment;
import net.gini.dropwizard.gelf.filters.GelfLoggingFilter;

import javax.servlet.DispatcherType;
import javax.ws.rs.ext.Provider;
import java.util.EnumSet;

@Provider
public class GrayLogCollectFeature {
    @Inject
    GrayLogCollectFeature(Environment environment) {
        environment.servlets()
                .addFilter("request-logs", new GelfLoggingFilter())
                .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}

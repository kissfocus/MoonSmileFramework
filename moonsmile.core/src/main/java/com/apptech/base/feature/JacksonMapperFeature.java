package com.apptech.base.feature;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.inject.Inject;
import io.dropwizard.jackson.AnnotationSensitivePropertyNamingStrategy;
import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import io.dropwizard.jackson.FuzzyEnumModule;
import io.dropwizard.setup.Environment;

import javax.ws.rs.ext.Provider;
import java.text.SimpleDateFormat;

@Provider
public class JacksonMapperFeature {
    @Inject
    JacksonMapperFeature(Environment environment) {
        ObjectMapper mapper = environment.getObjectMapper();
        mapper.registerModule(new JodaModule())
                .registerModule(new Jdk8Module())
                .registerModule(new FuzzyEnumModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
                .setSubtypeResolver(new DiscoverableSubtypeResolver())
                .setPropertyNamingStrategy(new AnnotationSensitivePropertyNamingStrategy());
    }
}

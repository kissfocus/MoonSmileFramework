package com.apptech.base.feature;

import com.google.inject.Inject;
import io.dropwizard.jersey.protobuf.InvalidProtocolBufferExceptionMapper;
import io.dropwizard.jersey.protobuf.ProtocolBufferMessageBodyProvider;
import io.dropwizard.setup.Environment;

import javax.ws.rs.ext.Provider;

@Provider
public class ProtobufSupportFeature {
    @Inject
    ProtobufSupportFeature(Environment environment){
        environment.jersey().register(new ProtocolBufferMessageBodyProvider());
        environment.jersey().register(new InvalidProtocolBufferExceptionMapper());
    }
}

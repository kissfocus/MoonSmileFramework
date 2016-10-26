package com.apptech.base.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneralFormator {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralFormator.class);

    public static String fromProtobufToJson(final Message message) {
        JsonFormat jsonFormat = new JsonFormat();
        return jsonFormat.printToString(message);
    }

    public static <T> T fromJsonToType(final String json, Class<T> valueType) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, valueType);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }
}

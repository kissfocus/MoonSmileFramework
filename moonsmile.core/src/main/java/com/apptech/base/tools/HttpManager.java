package com.apptech.base.tools;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HttpManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpManager.class);

    public static String getData(String url, Map<String, String> params) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return requestData(request);
    }

    public static String postData(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (String paramKey : params.keySet()) {
                builder.addEncoded(paramKey, params.get(paramKey));
            }
        }

        return postDataWithBody(url, builder.build());
    }

    public static String postDataWithBody(String url, RequestBody body) {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return requestData(request);
    }

    public static String requestData(Request request) {
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new Exception("Unexpected code " + response);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return "";
    }
}

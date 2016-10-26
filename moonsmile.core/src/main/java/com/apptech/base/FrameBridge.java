package com.apptech.base;

import com.apptech.base.model.SystemSetting;
import com.apptech.base.tools.GeneralFormator;
import com.apptech.base.tools.HttpManager;
import com.apptech.base.tools.RedisPoolCenter;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import io.dropwizard.setup.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FrameBridge {

    private static String APP_CONFIG = "APP_CONFIG";
    private static String SYS_SETTING = "SYS_SETTING";
    private static String APP_ENVIROMENT = "APP_ENVIROMENT";
    private static String CONFIG_FILENAME = "/systemconfig.json";

    private static Map<String, Object> bridgeSetting = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends FrameConfiguration> T getConfig() {
        return (T) bridgeSetting.getOrDefault(APP_CONFIG, null);
    }

    private static <T extends FrameConfiguration> T buildConfig(T config) {
        bridgeSetting.putIfAbsent(APP_CONFIG, config);
        return config;
    }

    public static SystemSetting getSetting() {
        return (SystemSetting) bridgeSetting.getOrDefault(SYS_SETTING, null);
    }

    private static <T extends FrameConfiguration> T buildSetting(T config) throws IOException {
        String url = config.getClientConfig().getUrl();
        String jsonData;
        if (config.getClientConfig().isUsingLocalConfig()) {
            jsonData = readConfigFile(CONFIG_FILENAME);
        } else {
            jsonData = HttpManager.postData(url, null);
        }

        SystemSetting setting = GeneralFormator.fromJsonToType(jsonData, SystemSetting.class);
        bridgeSetting.putIfAbsent(SYS_SETTING, setting);
        return config;
    }

    private static void buildEnviroment(Environment environment) {
        bridgeSetting.putIfAbsent(APP_ENVIROMENT, environment);
    }

    public static Environment getEnviroment() {
        return (Environment) bridgeSetting.getOrDefault(APP_ENVIROMENT, null);
    }

    public static String readConfigFile(String fileName) throws IOException {
        InputStream inputStream = FrameBridge.class.getResourceAsStream(fileName);
        return CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
    }

    public static <T extends FrameConfiguration> T init(T config, Environment environment) throws IOException {
        buildConfig(config);
        buildSetting(config);
        buildEnviroment(environment);

        //init redis
        if (config.getRedis() != null) {
            RedisPoolCenter.build(environment, config.getRedis());
        }

        return config;
    }
}

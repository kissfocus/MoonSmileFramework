package com.apptech.base.tools;

import com.apptech.base.FrameBridge;
import com.apptech.base.mapper.DBEndPointMapper;
import com.apptech.base.model.DBEndPoint;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.collect.ImmutableMap;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DBPoolCenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBPoolCenter.class);

    public enum DBType {
        NONE_OPTION,
        READ_WRITE,
        READ_ONLY
    }

    private static String LOCALFILE = "LOCALFILE";

    private static LoadingCache<String, DBEndPoint> configCache =
            CacheBuilder.newBuilder()
                    .maximumSize(1000)
                    .expireAfterWrite(10, TimeUnit.MINUTES)
                    .build(new CacheLoader<String, DBEndPoint>() {
                        @Override
                        public DBEndPoint load(String key) throws Exception {
                            return fetchDBConfig(key);
                        }
                    });

    private static LoadingCache<String, HikariDataSource> dataSourceCache =
            CacheBuilder.newBuilder()
                    .maximumSize(1000)
                    .expireAfterWrite(10, TimeUnit.MINUTES)
                    .removalListener(notification -> {
                        LOGGER.info("Following data is being removed:" + notification.getKey());
                        try (HikariDataSource dataSource = (HikariDataSource) notification.getValue()) {
                            if (dataSource != null) {
                                dataSource.close();
                            }
                        }

                        if (notification.getCause() == RemovalCause.EXPIRED) {
                            LOGGER.info("This data expired:" + notification.getKey());
                        } else {
                            LOGGER.info("This data didn't expired but evacuated intentionally" + notification.getKey());
                        }
                    })
                    .build(new CacheLoader<String, HikariDataSource>() {
                        @Override
                        public HikariDataSource load(String key) throws Exception {
                            return fetchDBDataSource(key);
                        }
                    });

    private static DBEndPoint fetchDBConfig(String name) throws IOException {
        //load db config from url
        //here need to change to load url from yml
        String clientConfig;
        String endPointUrl = FrameBridge.getSetting().getEndPointUrl();
        final List<String> nameList = Splitter.on(',').trimResults().splitToList(name);
        final String dbName = nameList.get(0);
        final String readWrite = nameList.get(1);

        List<String> readLocals = FrameBridge.getConfig().getClientConfig().getReadlocal();

        if (endPointUrl.equals(LOCALFILE) || readLocals.contains(dbName)) {
            clientConfig = FrameBridge.readConfigFile(String.format("/%s.json", dbName));
        } else {
            final Map<String, String> params = ImmutableMap.of("tenantcode", dbName, "readwrite", readWrite);
            clientConfig = HttpManager.postData(endPointUrl, params);
        }

        DBEndPoint endPoint = GeneralFormator.fromJsonToType(clientConfig, DBEndPoint.class);
        if (endPoint != null) {
            //Need To Decrypt
            String userName = EncryptUtils.decryptString(endPoint.getUsername());
            String password = EncryptUtils.decryptString(endPoint.getPassword());
            endPoint.setUsername(userName);
            endPoint.setPassword(password);
        }
        return endPoint;
    }

    private static HikariDataSource fetchDBDataSource(String name) {
        DBEndPoint endPoint = configCache.getUnchecked(name);
        HikariConfig config = DBEndPointMapper.mapToHikaricp(endPoint);
        Environment environment = FrameBridge.getEnviroment();
        config.setHealthCheckRegistry(environment.healthChecks());
        config.setMetricRegistry(environment.metrics());
        return new HikariDataSource(config);
    }

    public static DBI getDBI(String dbName, DBType dbtype) {
        String poolName = String.format("%s,%d", dbName, dbtype.ordinal());
        DBEndPoint endPoint = configCache.getUnchecked(poolName);
        if (Strings.isNullOrEmpty(endPoint.getPoolName())) {
            // if pool is empty,just return dbi
            new DBI(endPoint.getJdbcUrl(), endPoint.getUsername(), endPoint.getPassword());
        }

        HikariDataSource dataSource = dataSourceCache.getUnchecked(poolName);
        return new DBI(dataSource);
    }

    public static void close() {
        dataSourceCache.cleanUp();
        configCache.cleanUp();
    }
}

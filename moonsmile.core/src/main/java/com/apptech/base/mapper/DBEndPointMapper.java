package com.apptech.base.mapper;

import com.apptech.base.model.DBEndPoint;
import com.google.common.base.Strings;
import com.zaxxer.hikari.HikariConfig;

import java.util.Properties;

public class DBEndPointMapper {

    public static HikariConfig mapToHikaricp(final DBEndPoint config) {

        Properties prop = new Properties();
        prop.put("jdbcUrl", config.getJdbcUrl());
        prop.put("username", config.getUsername());
        prop.put("password", config.getPassword());
        prop.put("readOnly", config.isReadOnly());
        if (!Strings.isNullOrEmpty(config.getPoolName())) {
            prop.put("poolName", config.getPoolName());
            prop.put("maxLifetime", config.getMaxLifetime());
            prop.put("maximumPoolSize", config.getMaximumPoolSize());

            if (config.isCachePrepStmts()) {
                prop.put("dataSource.cachePrepStmts", config.isCachePrepStmts());
                prop.put("dataSource.prepStmtCacheSize", config.getPrepStmtCacheSize());
                prop.put("dataSource.prepStmtCacheSqlLimit", config.getPrepStmtCacheSqlLimit());
            }
        }

        return new HikariConfig(prop);
    }
}

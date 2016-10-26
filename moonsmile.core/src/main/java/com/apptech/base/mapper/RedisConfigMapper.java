package com.apptech.base.mapper;

import com.apptech.base.config.RedisClientConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConfigMapper {

    public static JedisPool mapToConnectPool(final RedisClientConfig config) {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(config.getMinIdle());
        poolConfig.setMaxIdle(config.getMaxIdle());
        poolConfig.setMaxTotal(config.getMaxTotal());

        return new JedisPool(poolConfig, config.getHost(), config.getPort(), config.getTimeout(), config.getPassword(), config.getSsl());
    }
}

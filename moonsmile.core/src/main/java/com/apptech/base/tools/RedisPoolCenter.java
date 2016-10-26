package com.apptech.base.tools;

import com.apptech.base.config.RedisClientConfig;
import com.apptech.base.mapper.RedisConfigMapper;
import com.codahale.metrics.Gauge;
import io.dropwizard.setup.Environment;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisPoolCenter {

    private static JedisPool redisPool = null;

    public static void build(Environment environment, RedisClientConfig clientConfig) {
        redisPool = RedisConfigMapper.mapToConnectPool(clientConfig);

        environment.metrics().register("redis.connection.idle", (Gauge) () -> redisPool.getNumIdle());
        environment.metrics().register("redis.connection.active", (Gauge) () -> redisPool.getNumActive());
        environment.metrics().register("redis.connection.wait", (Gauge) () -> redisPool.getNumWaiters());
    }

    public static boolean isInitial() {
        return redisPool != null;
    }

    public static Jedis getResource() {
        return redisPool.getResource();
    }

    public static void close() {
        if (redisPool != null) {
            redisPool.close();
        }
    }
}

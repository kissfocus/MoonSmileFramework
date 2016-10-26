package com.apptech.base.health;

import com.apptech.base.tools.RedisPoolCenter;
import redis.clients.jedis.Jedis;
import ru.vyarus.dropwizard.guice.module.installer.feature.health.NamedHealthCheck;

public class RedisHealthCheck extends NamedHealthCheck {
    @Override
    protected Result check() throws Exception {

        if (!RedisPoolCenter.isInitial()){
            return Result.healthy("Redis is not config");
        }

        try (Jedis jedis = RedisPoolCenter.getResource()) {
            final String pong = jedis.ping();
            if ("PONG".equals(pong)) {
                return Result.healthy();
            }
        }

        return Result.unhealthy("Could not ping redis");
    }

    @Override
    public String getName() {
        return "RedisHealth";
    }
}

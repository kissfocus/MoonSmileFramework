package com.apptech.cache.resources;

import com.apptech.base.tools.RedisPoolCenter;
import redis.clients.jedis.Jedis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("redis")
public class RedisResource {

    @Path("jobtime")
    @GET
    public Response jobTime() {
        String result;
        try (Jedis jedis = RedisPoolCenter.getResource()) {
            result = jedis.get("ServerTime");
        }

        return Response.ok(result).build();
    }

}

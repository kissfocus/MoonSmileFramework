package com.apptech.engine.resources;

import com.apptech.base.FrameBridge;
import com.apptech.base.tools.DBExecutor;
import com.apptech.base.tools.DBPoolCenter;
import com.apptech.base.tools.HttpManager;
import com.apptech.base.tools.RedisPoolCenter;
import com.apptech.engine.MetaEngineConfiguration;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import redis.clients.jedis.Jedis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("pageobject")
public class PageObjectResource {

    @Path("hello")
    @GET
    public Response helloWorld() {
        MetaEngineConfiguration config = FrameBridge.getConfig();
        String url = config.getClientConfig().getUrl();
        String data = HttpManager.getData(url, null);
        return Response.ok("Hello" + data).build();
    }

    @Path("hellolog")
    @GET
    public Response helloWorldLog() {
        return Response.ok("Hello:" + DateTime.now().toString()).build();
    }

    @Path("hellosql")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response helloWorldSql() {
        DBI dbi = DBPoolCenter.getDBI("xx_metadata", DBPoolCenter.DBType.READ_WRITE);
        String sql = "SELECT * FROM pageaction;";
        List<Map<String, Object>> result = DBExecutor.executeQuery(dbi, sql, null);
        return Response.ok(result).build();
    }

    @Path("redis")
    @GET
    public Response redisHello() {
        String result;
        try (Jedis jedis = RedisPoolCenter.getResource()) {
            jedis.set("hello", "crm");
            jedis.incr("HelloCRM");
            result = jedis.get("HelloCRM") + jedis.get("hello");
        }

        return Response.ok(result).build();
    }
}

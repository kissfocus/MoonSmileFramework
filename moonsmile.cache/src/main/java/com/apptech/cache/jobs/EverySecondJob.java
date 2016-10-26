package com.apptech.cache.jobs;

import com.apptech.base.tools.RedisPoolCenter;
import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.Every;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

//@On("0/15 * * * * ?")
@Every("5s")
public class EverySecondJob extends Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(EverySecondJob.class);

    @Override
    public void doJob() {
        LOGGER.info("JOB TIME:" + DateTime.now(DateTimeZone.getDefault()).toString("yyyy-MM-dd HH:mm:ss"));
        try(Jedis jedis = RedisPoolCenter.getResource()){
            jedis.set("ServerTime",DateTime.now(DateTimeZone.getDefault()).toString("yyyy-MM-dd HH:mm:ss"));
        }
    }
}

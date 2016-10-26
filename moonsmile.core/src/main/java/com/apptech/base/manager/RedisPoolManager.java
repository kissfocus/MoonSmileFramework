package com.apptech.base.manager;

import com.apptech.base.tools.RedisPoolCenter;
import io.dropwizard.lifecycle.Managed;

public class RedisPoolManager implements Managed {

    public RedisPoolManager() {

    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        RedisPoolCenter.close();
    }
}

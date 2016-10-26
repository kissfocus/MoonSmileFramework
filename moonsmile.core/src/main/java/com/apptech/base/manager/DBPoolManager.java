package com.apptech.base.manager;

import com.apptech.base.tools.DBPoolCenter;
import io.dropwizard.lifecycle.Managed;

public class DBPoolManager implements Managed {

    public DBPoolManager() {

    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        DBPoolCenter.close();
    }
}

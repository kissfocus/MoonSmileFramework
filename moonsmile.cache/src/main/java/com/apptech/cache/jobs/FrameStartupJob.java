package com.apptech.cache.jobs;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OnApplicationStart
public class FrameStartupJob extends Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrameStartupJob.class);

    @Override
    public void doJob() {
        // logic run once on application start
        LOGGER.info("System Start from jobs");
    }
}

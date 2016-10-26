package com.apptech.cache.jobs;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OnApplicationStop
public class FrameStopedJob extends Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrameStopedJob.class);

    @Override
    public void doJob() {
        // logic run once on application stop
        LOGGER.info("System will to stop");
    }
}

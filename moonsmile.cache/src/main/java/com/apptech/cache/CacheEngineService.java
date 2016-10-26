package com.apptech.cache;

import com.apptech.base.FrameService;
import com.apptech.cache.jobs.FrameStartupJob;
import de.spinscale.dropwizard.jobs.JobsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class CacheEngineService extends FrameService<CacheEngineConfiguration> {

    public static void main(String[] args) throws Exception {
        new CacheEngineService().run("server", System.getProperty("dropwizard.config"));
    }

    @Override
    public void initialize(Bootstrap<CacheEngineConfiguration> bootstrap) {
        super.initialize(bootstrap);

        GuiceBundle<CacheEngineConfiguration> guiceBundle = GuiceBundle.<CacheEngineConfiguration>builder()
                .enableAutoConfig(super.scanPackageName(), getClass().getPackage().getName())
                .noGuiceFilter()
                .printDiagnosticInfo()
                .build();
        bootstrap.addBundle(guiceBundle);

        //enable schedule job
        bootstrap.addBundle(new JobsBundle(FrameStartupJob.class.getPackage().getName()));
    }

    @Override
    public void run(CacheEngineConfiguration configuration, Environment environment) throws Exception {
        super.run(configuration, environment);
    }
}

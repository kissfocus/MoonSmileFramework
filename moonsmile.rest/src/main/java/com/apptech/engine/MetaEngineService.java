package com.apptech.engine;

import com.apptech.base.FrameService;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class MetaEngineService extends FrameService<MetaEngineConfiguration> {

    public static void main(String[] args) throws Exception {
        new MetaEngineService().run("server", System.getProperty("dropwizard.config"));
    }

    @Override
    public void initialize(Bootstrap<MetaEngineConfiguration> bootstrap) {
        super.initialize(bootstrap);

        GuiceBundle<MetaEngineConfiguration> guiceBundle = GuiceBundle.<MetaEngineConfiguration>builder()
                .enableAutoConfig(super.scanPackageName(), getClass().getPackage().getName())
                .noGuiceFilter()
                .printDiagnosticInfo()
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(MetaEngineConfiguration configuration, Environment environment) throws Exception {
        super.run(configuration, environment);
    }
}

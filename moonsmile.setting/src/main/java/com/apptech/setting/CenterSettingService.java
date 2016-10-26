package com.apptech.setting;

import com.apptech.base.FrameService;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class CenterSettingService extends FrameService<CenterSettingConfiguration> {

    public static void main(String[] args) throws Exception {
        new CenterSettingService().run("server", System.getProperty("dropwizard.config"));
    }

    @Override
    public void initialize(Bootstrap<CenterSettingConfiguration> bootstrap) {
        super.initialize(bootstrap);

        GuiceBundle<CenterSettingConfiguration> guiceBundle = GuiceBundle.<CenterSettingConfiguration>builder()
                .enableAutoConfig(super.scanPackageName(), getClass().getPackage().getName())
                .noGuiceFilter()
                .printDiagnosticInfo()
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(CenterSettingConfiguration configuration, Environment environment) throws Exception {
        super.run(configuration, environment);
    }
}

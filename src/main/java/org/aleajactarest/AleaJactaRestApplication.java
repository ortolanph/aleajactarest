package org.aleajactarest;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AleaJactaRestApplication extends Application<AleaJactaRestConfiguration> {

    private GuiceBundle<AleaJactaRestConfiguration> guiceBundle;

    public static void main(String[] args) {
        try {
            new AleaJactaRestApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(Bootstrap<AleaJactaRestConfiguration> bootstrap) {

        guiceBundle = GuiceBundle
                .<AleaJactaRestConfiguration>newBuilder()
                .addModule(new AleaJactaRestModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(AleaJactaRestConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
    }

    public void run(AleaJactaRestConfiguration dropBookWizardConfiguration, Environment environment) throws Exception {

    }
}

package org.aleajactarest;

import org.aleajactarest.service.DiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class AleaJactaRestApplication extends Application<AleaJactaRestConfiguration> {

    public static void main(String[] args) {
        try {
            new AleaJactaRestApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(AleaJactaRestConfiguration dropBookWizardConfiguration, Environment environment) throws Exception {
        DiceResource diceResource = new DiceResource();

        environment.jersey().register(diceResource);
    }
}

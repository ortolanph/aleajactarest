package org.aleajactarest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.logging.Logger;

@SpringBootApplication
public class AleaJactaRestApplication {

    private static final Logger LOGGER = Logger.getLogger(AleaJactaRestApplication.class.getName());

    public static void main(String[] args) {
        LOGGER.fine("ALEA, JACTA, REST! is running!");
        ApplicationContext ctx = SpringApplication.run(AleaJactaRestApplication.class, args);
    }
}

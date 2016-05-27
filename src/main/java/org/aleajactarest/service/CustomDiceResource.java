package org.aleajactarest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/custom")
@Produces(MediaType.APPLICATION_JSON)
public class CustomDiceResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomDiceResource.class);

    @GET
    @Path("{diceTemplate}/{diceValues}")
    public String roll(
            @PathParam("diceTemplate") String diceTemplate,
            @PathParam("diceValues") String diceValues) {

        LOGGER.info("Rolling a {}, with the following template: ", diceTemplate);

        List<String> dice = Arrays.asList(diceValues.split(","));

        dice.stream().forEach(d -> LOGGER.info("\t* {}", d));

        return "Implementing";
    }
}

package org.aleajactarest.service;

import com.google.inject.Inject;
import org.aleajactarest.assembly.CustomDiceRollResultAssembly;
import org.aleajactarest.beans.CustomDiceRollResult;
import org.aleajactarest.engine.Dice;
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

    @Inject
    private CustomDiceRollResultAssembly customAssembly;

    @GET
    @Path("{template}/{values}")
    public CustomDiceRollResult roll(
            @PathParam("template") String template,
            @PathParam("values") String values) {

        LOGGER.info("Rolling a {}, with the following template: ", template);

        List<String> faces = Arrays.asList(values.split(","));

        faces.stream().forEach(d -> LOGGER.info("\t* {}", d));

        int result = Dice.getDiceBySymbol(template).roll();

        return customAssembly.getResult(template, faces.get(result - 1), faces);
    }
}

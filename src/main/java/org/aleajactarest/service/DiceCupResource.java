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

@Path("/cup")
@Produces(MediaType.APPLICATION_JSON)
public class DiceCupResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiceCupResource.class);

    @GET
    @Path("{dicelist}")
    public List<String> roll(@PathParam("dicelist") String dicelist) {
        List<String> dices = Arrays.asList(dicelist.split(","));

        dices.stream().forEach(dice -> LOGGER.info("Rolling a {}, I'm sure that's a lot work to do...", dice));

        return dices;
    }

}

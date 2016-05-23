package org.aleajactarest.service;

import org.aleajactarest.engine.Dice;
import org.aleajactarest.beans.DiceRollResult;
import org.aleajactarest.beans.DiceRollResultAssembly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("/roll")
@Produces(MediaType.APPLICATION_JSON)
public class DiceResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiceResource.class);

    @GET
    @Path("{dice}")
    public DiceRollResult roll(
            @PathParam("dice") String dice) {

        LOGGER.info("Rolling a {}, shaking hand...",
                dice);

        Dice myDice = Dice.getDiceBySymbol(dice);

        DiceRollResultAssembly assembly = new DiceRollResultAssembly();

        return assembly.singleRoll(myDice, myDice.roll());
    }

    @GET
    @Path("{times}/{dice}")
    public DiceRollResult rollMultiple(
            @PathParam("times") int times,
            @PathParam("dice") String dice) {

        LOGGER.info(
                "Rolling {} {}s! There's a lot of shakes that I have to do.",
                times,
                dice);

        Dice myDice = Dice.getDiceBySymbol(dice);

        int[] partials = myDice.multipleRoll(times);

        int result = Arrays.stream(partials).sum();

        DiceRollResultAssembly assembly = new DiceRollResultAssembly();

        return assembly.multipleRoll(myDice, result, partials);
    }

    @GET
    @Path("{times}/{dice}/{modifier}")
    public DiceRollResult rollMultiple(
            @PathParam("times") int times,
            @PathParam("dice") String dice,
            @PathParam("modifier") int modifier) {

        LOGGER.info(
                "Rolling {} {}s and modified by {}! There's a lot of shakes that I have to do.",
                times,
                dice,
                modifier);

        Dice myDice = Dice.getDiceBySymbol(dice);

        int[] partials = myDice.multipleRoll(times);

        int result = Arrays.stream(partials).parallel().sum() + modifier;

        DiceRollResultAssembly assembly = new DiceRollResultAssembly();

        return assembly.rollWithAdditional(myDice, result, modifier, partials);
    }

    @GET
    @Path("/dice/{diceNotation}")
    public DiceRollResult rollWithNotation(
            @PathParam("diceNotation") String diceNotation) {

        LOGGER.info(
                "Interpreting {} and rolling.",
                diceNotation);

        return null;
    }

}

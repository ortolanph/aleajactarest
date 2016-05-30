package org.aleajactarest.service;

import com.google.inject.Inject;
import org.aleajactarest.assembly.DiceRollResultAssembly;
import org.aleajactarest.beans.DiceRollResult;
import org.aleajactarest.beans.ParsedDice;
import org.aleajactarest.engine.Dice;
import org.aleajactarest.operation.Operation;
import org.aleajactarest.parser.DiceNotationParser;
import org.aleajactarest.parser.exceptions.DiceParseException;
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

    @Inject
    private DiceRollResultAssembly assembly;

    @Inject
    private DiceNotationParser parser;

    @GET
    @Path("{dice}")
    public DiceRollResult roll(
            @PathParam("dice") String dice) {

        LOGGER.info("Rolling a {}, shaking hand...",
                dice);

        Dice myDice = Dice.getDiceBySymbol(dice);

        return assembly.singleRollResult(myDice, myDice.roll());
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

        return assembly.multipleRollResult(myDice, result, partials);
    }

    @GET
    @Path("{times}/{dice}/{operator}/{modifier}")
    public DiceRollResult rollMultiple(
            @PathParam("times") int times,
            @PathParam("dice") String dice,
            @PathParam("operator") String operator,
            @PathParam("modifier") int modifier) {

        LOGGER.info(
                "Rolling {} {}s and modified by {}{}! There's a lot of shakes that I have to do.",
                times,
                dice,
                operator,
                modifier);

        Dice myDice = Dice.getDiceBySymbol(dice);

        int[] partials = myDice.multipleRoll(times);

        int result = Operation.getOperationBySymbol(operator).compute(Arrays.stream(partials).parallel().sum(), modifier);

        return assembly.modifierResult(myDice, result, operator, modifier, partials);
    }

    @GET
    @Path("/dice/{diceNotation}")
    public DiceRollResult rollWithNotation(
            @PathParam("diceNotation") String diceNotation) throws DiceParseException {

        LOGGER.info(
                "Interpreting {} and rolling.",
                diceNotation);

        ParsedDice parsedDice = parser.evaluate(diceNotation);

        Dice myDice = Dice.getDiceBySymbol(parsedDice.getDice());

        int[] partials = myDice.multipleRoll(parsedDice.getAmount());

        int result = Operation.getOperationBySymbol(parsedDice.getOperator()).compute(Arrays.stream(partials).parallel().sum(), parsedDice.getModifier());

        return assembly.modifierResult(myDice, result, parsedDice.getOperator(), parsedDice.getModifier(), partials);
    }

}

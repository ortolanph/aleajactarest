package org.aleajactarest.service;

import com.google.inject.Inject;
import org.aleajactarest.assembly.DiceRollResultAssembly;
import org.aleajactarest.beans.DiceCupRollResult;
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
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

@Path("/cup")
@Produces(MediaType.APPLICATION_JSON)
public class DiceCupResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiceCupResource.class);

    @Inject
    private DiceNotationParser parser;

    @Inject
    private DiceRollResultAssembly assembly;

    @GET
    @Path("{dicelist}")
    public DiceCupRollResult roll(@PathParam("dicelist") String dicelist) {
        List<String> dices = Arrays.asList(dicelist.split(","));

        LOGGER.info("Will roll: ");

        dices.stream().forEach(dice -> LOGGER.info("\t - {}", dice));

        List<ParsedDice> parsedDices = newArrayList();
        List<DiceRollResult> results = newArrayList();

        dices.stream().forEach(dice -> {
            try {
                parsedDices.add(parser.evaluate(dice));
            } catch (DiceParseException e) {
                e.printStackTrace();
            }
        });

        results.addAll(parsedDices.stream().map(pd -> roll(pd)).collect(Collectors.toList()));
        int sum = results.stream().mapToInt(t -> t.getResult()).sum();

        DiceCupRollResult diceCupRollResult = new DiceCupRollResult();

        diceCupRollResult.setDices(results);
        diceCupRollResult.setMasterResult(sum);

        return diceCupRollResult;
    }

    private DiceRollResult roll(ParsedDice parsedDice) {
        Dice myDice = Dice.getDiceBySymbol(parsedDice.getDice());

        int[] partials = myDice.multipleRoll(parsedDice.getAmount());

        int result = Operation.getOperationBySymbol(parsedDice.getOperator()).compute(Arrays.stream(partials).parallel().sum(), parsedDice.getModifier());

        return assembly.modifierResult(myDice, result, parsedDice.getOperator(), parsedDice.getModifier(), partials);
    }
}

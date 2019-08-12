package org.aleajactarest.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/dices/cup")
public class DiceCupResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiceCupResource.class);

    @Autowired
    private DiceNotationParser parser;

    @Autowired
    private DiceRollResultAssembly assembly;

    @GetMapping("/{dicelist}")
    public DiceCupRollResult roll(@PathVariable("dicelist") String dicelist) {
        List<String> dices = Arrays.asList(dicelist.split(","));

        LOGGER.info("Will roll: ");

        dices.stream().forEach(dice -> LOGGER.info("\t - {}", dice));

        List<ParsedDice> parsedDices = new ArrayList<>();
        List<DiceRollResult> results = new ArrayList<>();

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

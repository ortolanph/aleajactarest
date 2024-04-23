package org.aleajactarest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aleajactarest.assembly.DiceRollResultAssembly;
import org.aleajactarest.beans.DiceCupRollResult;
import org.aleajactarest.beans.DiceRollResult;
import org.aleajactarest.beans.ParsedDice;
import org.aleajactarest.engine.Dice;
import org.aleajactarest.operation.Operation;
import org.aleajactarest.parser.DiceNotationParser;
import org.aleajactarest.parser.exceptions.DiceParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/api/dices/cup")
public class DiceCupResource {

    final DiceNotationParser parser;

    final DiceRollResultAssembly assembly;

    @GetMapping("/{dicelist}")
    public DiceCupRollResult roll(@PathVariable("dicelist") String dicelist) {
        List<String> dices = Arrays.asList(dicelist.split(","));

        log.info("Will roll: ");

        dices.forEach(dice -> log.info("\t - {}", dice));

        List<ParsedDice> parsedDices = new ArrayList<>();

        dices.forEach(dice -> {
            try {
                parsedDices.add(parser.evaluate(dice));
            } catch (DiceParseException e) {
                e.printStackTrace();
            }
        });

        List<DiceRollResult> results = parsedDices.stream().map(this::roll).collect(Collectors.toList());
        int sum = results.stream().mapToInt(DiceRollResult::getResult).sum();

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

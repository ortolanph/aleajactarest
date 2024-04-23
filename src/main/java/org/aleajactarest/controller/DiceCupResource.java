package org.aleajactarest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aleajactarest.assembly.DiceRollResultAssembly;
import org.aleajactarest.beans.DiceCupRollResult;
import org.aleajactarest.beans.DiceRollResult;
import org.aleajactarest.beans.ParsedDice;
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

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/api/dices/cup")
public class DiceCupResource implements RollResources {

    final DiceNotationParser parser;

    final DiceRollResultAssembly assembly;

    @GetMapping("/{dicelist}")
    public DiceCupRollResult diceCupRoll(@PathVariable("dicelist") String dicelist) {
        List<String> dices = Arrays.asList(dicelist.split(","));

        log.info("diceCupRoll: diceList {}", dicelist);

        List<ParsedDice> parsedDices = new ArrayList<>();

        dices.forEach(dice -> {
            try {
                parsedDices.add(parser.evaluate(dice));
            } catch (DiceParseException e) {
                log.error("Error parsing dice", e);
            }
        });

        List<DiceRollResult> results = parsedDices
            .stream()
            .map(parsedDice -> getDiceRollResult(parsedDice, assembly))
            .toList();

        int sum = results.stream().mapToInt(DiceRollResult::getResult).sum();

        DiceCupRollResult diceCupRollResult = new DiceCupRollResult();

        diceCupRollResult.setDices(results);
        diceCupRollResult.setMasterResult(sum);

        return diceCupRollResult;
    }
}

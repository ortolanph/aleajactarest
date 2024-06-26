package org.aleajactarest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aleajactarest.assembly.DiceRollResultAssembly;
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

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/api/dices/roll")
public class DiceResource implements RollResources{

    final DiceRollResultAssembly assembly;

    final DiceNotationParser parser;

    @GetMapping("/{dice}")
    public DiceRollResult roll(
        @PathVariable("dice") String dice) {

        log.info("roll: dice {}", dice);

        Dice myDice = Dice.getDiceBySymbol(dice);

        return assembly.singleRollResult(myDice, myDice.roll());
    }

    @GetMapping("/{times}/{dice}")
    public DiceRollResult rollMultiple(
        @PathVariable("times") int times,
        @PathVariable("dice") String dice) {

        log.info("rollMultiple: times {} dice {}", times, dice);

        Dice myDice = Dice.getDiceBySymbol(dice);

        int[] partials = myDice.multipleRoll(times);

        int result = Arrays.stream(partials).sum();

        return assembly.multipleRollResult(myDice, result, partials);
    }

    @GetMapping("/{times}/{dice}/{operator}/{modifier}")
    public DiceRollResult rollMultipleWithOperation(
        @PathVariable("times") int times,
        @PathVariable("dice") String dice,
        @PathVariable("operator") String operator,
        @PathVariable("modifier") int modifier) {

        log.info("rollMultipleWithOperation: times {} dice {} operator {} modifier {}", times, dice, operator, modifier);

        Dice myDice = Dice.getDiceBySymbol(dice);

        int[] partials = myDice.multipleRoll(times);

        int result = Operation.getOperationBySymbol(operator).compute(Arrays.stream(partials).parallel().sum(), modifier);

        return assembly.modifierResult(myDice, result, operator, modifier, partials);
    }

    @GetMapping("/notation/{diceNotation}")
    public DiceRollResult rollWithNotation(
        @PathVariable("diceNotation") String diceNotation) throws DiceParseException {

        log.info("rollWithNotation: diceNotation {}", diceNotation);

        ParsedDice parsedDice = parser.evaluate(diceNotation);

        return getDiceRollResult(parsedDice, assembly);
    }

}

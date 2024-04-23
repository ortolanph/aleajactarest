package org.aleajactarest.controller;

import org.aleajactarest.assembly.DiceRollResultAssembly;
import org.aleajactarest.beans.DiceRollResult;
import org.aleajactarest.beans.ParsedDice;
import org.aleajactarest.engine.Dice;
import org.aleajactarest.operation.Operation;

import java.util.Arrays;

public interface RollResources {

    default DiceRollResult getDiceRollResult(ParsedDice parsedDice, DiceRollResultAssembly assembly) {
        Dice myDice = Dice.getDiceBySymbol(parsedDice.getDice());

        int[] partials = myDice.multipleRoll(parsedDice.getAmount());

        int result = Operation.getOperationBySymbol(parsedDice.getOperator()).compute(Arrays.stream(partials).parallel().sum(), parsedDice.getModifier());

        return assembly.modifierResult(myDice, result, parsedDice.getOperator(), parsedDice.getModifier(), partials);
    }
}

package org.aleajactarest.assembly;

import org.aleajactarest.beans.DiceRollResult;
import org.aleajactarest.engine.Dice;
import org.springframework.stereotype.Service;

@Service
public class DiceRollResultAssembly {

    public static final String DEFAULT_OPERATOR = "+";
    public static final int DEFAULT_MODIFIER = 0;

    public DiceRollResult singleRollResult(Dice dice, int result) {
        return createAResult(dice, result, DEFAULT_OPERATOR, DEFAULT_MODIFIER, new int[]{result});
    }

    public DiceRollResult multipleRollResult(Dice dice, int result, int[] partials) {
        return createAResult(dice, result, DEFAULT_OPERATOR, DEFAULT_MODIFIER, partials);
    }

    public DiceRollResult modifierResult(Dice dice, int result, String operator, int modifier, int[] partials) {
        return createAResult(dice, result, operator, modifier, partials);
    }

    private DiceRollResult createAResult(Dice dice, int result, String operator, int modifier, int[] partials) {
        DiceRollResult myResult = new DiceRollResult();

        myResult.setDice(dice.name().toLowerCase());
        myResult.setResult(result);
        myResult.setOperator(operator);
        myResult.setModifier(modifier);
        myResult.setPartials(partials);

        return myResult;
    }
}

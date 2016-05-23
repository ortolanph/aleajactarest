package org.aleajactarest.beans;

import com.google.common.base.Strings;
import org.aleajactarest.engine.Dice;

public class DiceRollResultAssembly {

    public DiceRollResult singleRoll(Dice dice, int result) {
        return createAResult(dice, result, new int[]{result}, 0);
    }

    public DiceRollResult multipleRoll(Dice dice, int result, int[] partials) {
        return createAResult(dice, result, partials, 0);
    }

    public DiceRollResult rollWithAdditional(Dice dice, int result, int modifier, int[] partials) {
        return createAResult(dice, result, partials, modifier);
    }

    private DiceRollResult createAResult(Dice dice, int result, int[] partials, int modifier) {
        DiceRollResult myResult = new DiceRollResult();

        myResult.setDice(dice.name().toLowerCase());
        myResult.setResult(result);
        myResult.setPartials(partials);
        myResult.setModifier(modifier);
        myResult.setOperator((modifier == 0)?"none":"+");

        return myResult;
    }
}

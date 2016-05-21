package org.aleajactarest.engine;

public class DiceRollResultAssembly {

    public DiceRollResult singleRoll(Dice dice, int result) {
        return createAResult(dice, result, new int[]{result}, 0);
    }

    public DiceRollResult multipleRoll(Dice dice, int result, int[] partials) {
        return createAResult(dice, result, partials, 0);
    }

    public DiceRollResult rollWithAdditional(Dice dice, int result, int modificator, int[] partials) {
        return createAResult(dice, result, partials, modificator);
    }

    private DiceRollResult createAResult(Dice dice, int result, int[] partials, int modificator) {
        DiceRollResult myResult = new DiceRollResult();

        myResult.setDice(dice.name().toLowerCase());
        myResult.setResult(result);
        myResult.setPartials(partials);
        myResult.setModificator(modificator);

        return myResult;
    }
}

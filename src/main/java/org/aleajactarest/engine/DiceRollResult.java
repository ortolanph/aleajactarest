package org.aleajactarest.engine;

public class DiceRollResult {
    private String dice;
    private int modificator;
    private int result;
    private int[] partials;

    public String getDice() {
        return dice;
    }

    public void setDice(String dice) {
        this.dice = dice;
    }

    public int[] getPartials() {
        return partials;
    }

    public void setPartials(int[] partials) {
        this.partials = partials;
    }

    public int getModificator() {
        return modificator;
    }

    public void setModificator(int modificator) {
        this.modificator = modificator;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}

package org.aleajactarest.beans;

public class DiceRollResult {
    private String dice;
    private int result;
    private String operator;
    private int modifier;
    private int[] partials;

    public String getDice() {
        return dice;
    }

    public void setDice(String dice) {
        this.dice = dice;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public int[] getPartials() {
        return partials;
    }

    public void setPartials(int[] partials) {
        this.partials = partials;
    }
}

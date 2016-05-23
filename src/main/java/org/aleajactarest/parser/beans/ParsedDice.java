package org.aleajactarest.parser.beans;

public class ParsedDice {
    private int quantifier;
    private String dice;
    private String operation;
    private int modifier;

    public int getQuantifier() {
        return quantifier;
    }

    public void setQuantifier(int quantifier) {
        this.quantifier = quantifier;
    }

    public String getDice() {
        return dice;
    }

    public void setDice(String dice) {
        this.dice = dice;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }
}

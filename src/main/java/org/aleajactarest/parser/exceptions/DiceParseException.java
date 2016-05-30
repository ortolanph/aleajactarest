package org.aleajactarest.parser.exceptions;

public class DiceParseException extends Exception {

    public DiceParseException(String diceExpression) {
        super(String.format("Can't parse %s", diceExpression));
    }
}

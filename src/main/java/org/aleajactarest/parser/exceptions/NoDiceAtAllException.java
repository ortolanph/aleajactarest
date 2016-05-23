package org.aleajactarest.parser.exceptions;

public class NoDiceAtAllException extends DiceParseException {


    public NoDiceAtAllException(String diceExpression) {
        super("That's not a dice at all, there's no 'd' in it");
    }
}

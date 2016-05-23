package org.aleajactarest.parser;

import org.aleajactarest.parser.beans.ParsedDice;
import org.aleajactarest.parser.exceptions.DiceParseException;
import org.aleajactarest.parser.exceptions.NoDiceAtAllException;

public class DiceNotationParser {

    private static final String DICE_SYMBOL = "d";
    private static final char[] OPERATORS = new char[]{'+', '-', '*', '÷'};

    public ParsedDice evaluate(String diceNotation) throws DiceParseException {
        int quantifier=0;
        String dice="";
        String operation="";
        int modifier=0;

        if (isValidDice(diceNotation)) {

        } else {
            throw new NoDiceAtAllException(diceNotation);
        }

        ParsedDice parsedDice = new ParsedDice();

        parsedDice.setQuantifier(quantifier);
        parsedDice.setDice(dice);
        parsedDice.setOperation(operation);
        parsedDice.setModifier(modifier);

        return new ParsedDice();
    }

    private boolean isValidDice(String diceNotation) {
        return diceNotation.contains(DICE_SYMBOL);
    }
}

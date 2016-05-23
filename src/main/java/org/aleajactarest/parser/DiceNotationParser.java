package org.aleajactarest.parser;

import com.google.common.base.Strings;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class DiceNotationParser {

    private static final String DICE_SYMBOL = "d";
    private static final List<String> OPERATORS = newArrayList("+", "-", "*", "÷");
    private static final int DEFAULT_AMOUNT = 1;
    private static final String DEFAULT_OPERATION = "+";
    private static final int DEFAULT_MODIFIER = 0;

    public ParsedDice evaluate(String diceNotation) throws DiceParseException {
        int amount = DEFAULT_AMOUNT;
        String dice = "";
        String operation = DEFAULT_OPERATION;
        int modifier = DEFAULT_MODIFIER;

        if (containsADice(diceNotation)) {
            String[] elements = diceNotation.split(DICE_SYMBOL);
            amount = getQuantifier(elements[0]);

            if (containsOperator(elements[1])) {
                operation = getOperation(elements[1]);
                modifier = Integer.valueOf(elements[1].substring(elements[1].indexOf(operation) + 1));
                elements[1] = elements[1].substring(0, elements[1].indexOf(operation));
            }

            dice = DICE_SYMBOL + elements[1];
        } else {
            throw new DiceParseException(diceNotation);
        }

        ParsedDice parsedDice = new ParsedDice();

        parsedDice.setAmount(amount);
        parsedDice.setDice(dice);
        parsedDice.setOperation(operation);
        parsedDice.setModifier(modifier);

        return parsedDice;
    }

    private boolean containsADice(String diceNotation) {
        return diceNotation.contains(DICE_SYMBOL);
    }

    private int getQuantifier(String quantifier) {
        return (!Strings.isNullOrEmpty(quantifier)) ? Integer.valueOf(quantifier) : DEFAULT_AMOUNT;
    }

    private boolean containsOperator(String diceNotation) {
        return OPERATORS
                .stream()
                .anyMatch(op -> diceNotation.contains(op));

    }

    private String getOperation(String diceNotation) {
        return OPERATORS
                .stream()
                .filter(op -> diceNotation.contains(op))
                .findFirst()
                .orElse(DEFAULT_OPERATION);
    }
}

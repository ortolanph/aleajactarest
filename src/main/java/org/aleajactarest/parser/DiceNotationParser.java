package org.aleajactarest.parser;

import org.aleajactarest.beans.ParsedDice;
import org.aleajactarest.parser.exceptions.DiceParseException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DiceNotationParser extends Throwable {

    private static final String DICE_SYMBOL = "d";
    private static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "÷");
    private static final int DEFAULT_AMOUNT = 1;
    private static final String DEFAULT_OPERATOR = "+";
    private static final int DEFAULT_MODIFIER = 0;

    public ParsedDice evaluate(String diceNotation) throws DiceParseException {
        int amount;
        String dice;
        String operator = DEFAULT_OPERATOR;
        int modifier = DEFAULT_MODIFIER;

        if (containsADice(diceNotation)) {
            String[] elements = diceNotation.split(DICE_SYMBOL);
            amount = getQuantifier(elements[0]);

            if (containsOperator(elements[1])) {
                operator = getOperation(elements[1]);
                modifier = Integer.parseInt(elements[1].substring(elements[1].indexOf(operator) + 1));
                elements[1] = elements[1].substring(0, elements[1].indexOf(operator));
            }

            dice = DICE_SYMBOL + elements[1];
        } else {
            throw new DiceParseException(diceNotation);
        }

        ParsedDice parsedDice = new ParsedDice();

        parsedDice.setAmount(amount);
        parsedDice.setDice(dice);
        parsedDice.setOperator(operator);
        parsedDice.setModifier(modifier);

        return parsedDice;
    }

    private boolean containsADice(String diceNotation) {
        return diceNotation.contains(DICE_SYMBOL);
    }

    private int getQuantifier(String quantifier) {
        return (quantifier.isBlank()) ? DEFAULT_AMOUNT : Integer.parseInt(quantifier);
    }

    private boolean containsOperator(String diceNotation) {
        return OPERATORS
            .stream()
            .anyMatch(diceNotation::contains);

    }

    private String getOperation(String diceNotation) {
        return OPERATORS
            .stream()
            .filter(diceNotation::contains)
            .findFirst()
            .orElse(DEFAULT_OPERATOR);
    }
}

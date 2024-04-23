package org.aleajactarest.parser;

import org.aleajactarest.beans.ParsedDice;
import org.aleajactarest.parser.exceptions.DiceParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiceNotationParserTest {

    DiceNotationParser parser = new DiceNotationParser();

    @Test
    void noDiceAtAll() {
        assertThrows(DiceParseException.class, () -> parser.evaluate("roll!"));
    }

    @Test
    void parseDiceOnly() throws Exception {
        ParsedDice expected = createParsedDice(0, "", 0);

        ParsedDice actual = parser.evaluate("d6");

        assertEquals(expected.getDice(), actual.getDice());
    }

    @Test
    void parseDiceWithAmout() throws Exception {
        ParsedDice expected = createParsedDice(2, "", 0);

        ParsedDice actual = parser.evaluate("2d6");

        assertEquals(expected.getAmount(), actual.getAmount());
    }

    @Test
    void parseDiceWithModifier() throws Exception {
        ParsedDice expected = createParsedDice(0, "*", 10);

        ParsedDice actual = parser.evaluate("d6*10");

        assertAll(
            () -> assertEquals(expected.getOperator(), actual.getOperator()),
            () -> assertEquals(expected.getModifier(), actual.getModifier())
        );
    }

    @Test
    void parseWithEverything() throws Exception {
        ParsedDice expected = createParsedDice(2, "*", 10);

        ParsedDice actual = parser.evaluate("2d6*10");

        assertAll(
            () -> assertEquals(expected.getAmount(), actual.getAmount()),
            () -> assertEquals(expected.getDice(), actual.getDice()),
            () -> assertEquals(expected.getOperator(), actual.getOperator()),
            () -> assertEquals(expected.getModifier(), actual.getModifier())
        );
    }


    ParsedDice createParsedDice(int quantifier, String operation, int modifier) {
        ParsedDice parsedDice = new ParsedDice();

        parsedDice.setAmount(quantifier);
        parsedDice.setDice("d6");
        parsedDice.setOperator(operation);
        parsedDice.setModifier(modifier);

        return parsedDice;
    }
}

package org.aleajactarest.parser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiceNotationParserTest {

    private DiceNotationParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new DiceNotationParser();
    }

    @Test(expected = DiceParseException.class)
    public void noDiceAtAll() throws Exception {
        parser.evaluate("roll!");
    }

    @Test
    public void parseDiceOnly() throws Exception {
        ParsedDice expected = createParsedDice(0, "d6", "", 0);

        ParsedDice actual = parser.evaluate("d6");

        assertEquals(expected.getDice(), actual.getDice());
    }

    @Test
    public void parseDiceWithAmout() throws Exception {
        ParsedDice expected = createParsedDice(2, "d6", "", 0);

        ParsedDice actual = parser.evaluate("2d6");

        assertEquals(expected.getAmount(), actual.getAmount());
    }

    @Test
    public void parseDiceWithModifier() throws Exception {
        ParsedDice expected = createParsedDice(0, "d6", "*", 10);

        ParsedDice actual = parser.evaluate("d6*10");


        assertEquals(expected.getOperator(), actual.getOperator());
        assertEquals(expected.getModifier(), actual.getModifier());
    }

    @Test
    public void parseWithEverything() throws Exception {
        ParsedDice expected = createParsedDice(2, "d6", "*", 10);

        ParsedDice actual = parser.evaluate("2d6*10");

        assertEquals(expected.getAmount(), actual.getAmount());
        assertEquals(expected.getDice(), actual.getDice());
        assertEquals(expected.getOperator(), actual.getOperator());
        assertEquals(expected.getModifier(), actual.getModifier());
    }


    private ParsedDice createParsedDice(int quantifier, String dice, String operation, int modifier) {
        ParsedDice parsedDice = new ParsedDice();

        parsedDice.setAmount(quantifier);
        parsedDice.setDice(dice);
        parsedDice.setOperator(operation);
        parsedDice.setModifier(modifier);

        return parsedDice;
    }
}
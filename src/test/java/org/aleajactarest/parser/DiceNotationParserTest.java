package org.aleajactarest.parser;

import org.aleajactarest.parser.beans.ParsedDice;
import org.aleajactarest.parser.exceptions.NoDiceAtAllException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiceNotationParserTest {

    private DiceNotationParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new DiceNotationParser();
    }

    @Test(expected = NoDiceAtAllException.class)
    public void noDiceAtAll() throws Exception {
        parser.evaluate("roll!");
    }

    @Test
    @Ignore
    public void parseDiceOnly() throws Exception {
        ParsedDice expected = createParsedDice(0, "d6", "", 0);

        ParsedDice actual = parser.evaluate("d6");

        assertEquals(expected.getQuantifier(), actual.getQuantifier());
        assertEquals(expected.getDice(), actual.getDice());
        assertEquals(expected.getOperation(), actual.getOperation());
        assertEquals(expected.getModifier(), actual.getModifier());
    }


    private ParsedDice createParsedDice(int quantifier, String dice, String operation, int modifier) {
        ParsedDice parsedDice = new ParsedDice();

        parsedDice.setQuantifier(quantifier);
        parsedDice.setDice(dice);
        parsedDice.setOperation(operation);
        parsedDice.setModifier(modifier);

        return parsedDice;
    }
}
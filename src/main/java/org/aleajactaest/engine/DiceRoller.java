package org.aleajactaest.engine;

/**
 * This is the base interface to rolling dices.
 *
 * @author Paulo Henrique Ortolan
 */
public interface DiceRoller {

    /**
     * Rolls a dice.
     *
     * @return the result of rolling a dice.
     */
    long roll();

    /**
     * Rolls a dice multiple times.
     *
     * @param times how many times a dice have to be rolled.
     * @return the results of rolling a dice.
     */
    long[] rollTimes(int times);
}

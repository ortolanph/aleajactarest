package org.aleajactaest.engine;

import java.util.Random;

/**
 * The set of different dices.
 *
 * @author Paulo Henrique Ortolan
 */
public enum Dice implements DiceRoller, DiceFunction {
    D4(4), D6(6), D8(8), D10(10), D12(12), D20(20), D100(100);

    private int faces;

    private Dice(int faces) {
        this.faces = faces;
    }

    @Override
    public long roll() {
        Random random = new Random();
        return random.nextInt(faces);
    }

    @Override
    public long[] rollTimes(int times) {
        long[] result = new long[times];


        return result;
    }

    @Override
    public Integer getRollingResult(int faces) {
        return 0;
    }
}

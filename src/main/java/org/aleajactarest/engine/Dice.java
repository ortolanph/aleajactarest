package org.aleajactarest.engine;

import java.util.Arrays;
import java.util.Random;

public enum Dice implements DiceRoller {
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20);

    private final int faces;

    Dice(int faces) {
        this.faces = faces;
    }

    public static Dice getDiceBySymbol(String symbol) {
        return Arrays
                .stream(values())
                .filter(d -> symbol.toUpperCase().equals(d.name()))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(String.format("No dice with symbol [%s]. Can't roll!", symbol))
                );
    }

    public int roll() {
        Random random = new Random();
        return random.nextInt(faces) + 1;
    }

    public int[] multipleRoll(int times) {
        return Arrays.stream(new int[times]).map(d -> roll()).toArray();
    }
}

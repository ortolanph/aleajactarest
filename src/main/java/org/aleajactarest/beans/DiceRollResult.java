package org.aleajactarest.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiceRollResult {
    private String dice;
    private int result;
    private String operator;
    private int modifier;
    private int[] partials;
}

package org.aleajactarest.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParsedDice {
    private int amount;
    private String dice;
    private String operator;
    private int modifier;
}

package org.aleajactarest.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DiceCupRollResult {
    private Integer masterResult;
    private List<DiceRollResult> dices;
}

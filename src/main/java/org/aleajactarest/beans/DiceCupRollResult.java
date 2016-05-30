package org.aleajactarest.beans;

import java.util.List;

public class DiceCupRollResult {
    private Integer masterResult;
    private List<DiceRollResult> dices;

    public Integer getMasterResult() {
        return masterResult;
    }

    public void setMasterResult(Integer masterResult) {
        this.masterResult = masterResult;
    }

    public List<DiceRollResult> getDices() {
        return dices;
    }

    public void setDices(List<DiceRollResult> dices) {
        this.dices = dices;
    }
}

package org.aleajactarest.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomDiceRollResult {
    private String template;
    private String result;
    private List<String> faces;

}

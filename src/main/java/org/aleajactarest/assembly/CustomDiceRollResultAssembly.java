package org.aleajactarest.assembly;

import org.aleajactarest.beans.CustomDiceRollResult;

import java.util.List;

public class CustomDiceRollResultAssembly {

    public CustomDiceRollResult getResult(String template, String result, List<String> faces) {
        CustomDiceRollResult customDiceRollResult = new CustomDiceRollResult();

        customDiceRollResult.setTemplate(template);
        customDiceRollResult.setResult(result);
        customDiceRollResult.setFaces(faces);

        return customDiceRollResult;
    }

}

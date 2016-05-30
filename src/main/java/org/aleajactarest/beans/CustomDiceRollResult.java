package org.aleajactarest.beans;

import java.util.List;

public class CustomDiceRollResult {
    private String template;
    private String result;
    private List<String> faces;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getFaces() {
        return faces;
    }

    public void setFaces(List<String> faces) {
        this.faces = faces;
    }
}

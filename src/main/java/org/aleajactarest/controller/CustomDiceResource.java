package org.aleajactarest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aleajactarest.assembly.CustomDiceRollResultAssembly;
import org.aleajactarest.beans.CustomDiceRollResult;
import org.aleajactarest.engine.Dice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(value = "/api/dices/custom")
public class CustomDiceResource implements RollResources {

    final CustomDiceRollResultAssembly customAssembly;

    @GetMapping("/{template}/{values}")
    public CustomDiceRollResult templateRoll(
        @PathVariable("template") String template,
        @PathVariable("values") String values) {

        log.info("templateRoll: template {} values {}", template, values);

        List<String> faces = Arrays.asList(values.split(","));

        int result = Dice.getDiceBySymbol(template).roll();

        return customAssembly.getResult(template, faces.get(result - 1), faces);
    }
}

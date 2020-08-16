package org.aleajactarest.controller;

import org.aleajactarest.assembly.CustomDiceRollResultAssembly;
import org.aleajactarest.beans.CustomDiceRollResult;
import org.aleajactarest.engine.Dice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/dices/custom")
public class CustomDiceResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomDiceResource.class);

    @Autowired
    private CustomDiceRollResultAssembly customAssembly;

    @GetMapping("/{template}/{values}")
    public CustomDiceRollResult roll(
            @PathVariable("template") String template,
            @PathVariable("values") String values) {

        LOGGER.info("Rolling a {}, with the following values: ", template);

        List<String> faces = Arrays.asList(values.split(","));

        faces.forEach(d -> LOGGER.info("\t* {}", d));

        int result = Dice.getDiceBySymbol(template).roll();

        return customAssembly.getResult(template, faces.get(result - 1), faces);
    }
}

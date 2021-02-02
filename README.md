# Alea, Jacta, REST!

## About

Dice rolling with REST interface.

## Try

Download the [Alea Jacta Rest Postman Collection](Alea Jacta Rest Collection.postman_collections.json) file, import it into Postman and give a try.

## Load Tests with Vegeta

Firsty build the project with the following command:

```shell
./gradlew clean build docker generateDockerCompose dockerComposeUp
```

After building, go to the `vegeta/local` directory and run:

```shell
./vegeta.sh
```

And watch things working.

## Dices

These are the dices included in the project:

* `d4` - a four sided dice, it's a pyramid with a triangular basis
* `d6` - a four sided dice, it's a regular cube
* `d8` - an eight sided dice, it's an octahedron
* `d10` - a ten sided dice, it's a pentagonal trapezohedron
* `d12` -  a twelve sided dice, it's a dodecahedron
* `d20` - a twenty sided dice, it's the famous icosahedron

## API

### Simple Roll

All the following API rolls only one dice.

#### Rolls one dice

```
http://aleajactarest.herokuapp.com/api/dices/roll/d{4,6,8,10,12,20}
```

Where:
 * `d{4,6,8,10,12,20}` is the dice to roll

Example:

```
http://aleajactarest.herokuapp.com/api/dices/roll/d20
```

Will result in:

```json
{
    "dice": "d20",
    "result": 9,
    "operator": "+",
    "modifier": 0,
    "partials": [
        9
    ]
}
```

#### Rolls one dice 

```
http://aleajactarest.herokuapp.com/api/dices/roll/{n}/d{4,6,8,10,12,20}
```

Where:
 * `n` is the number of times a dice is rolled
 * `d{4,6,8,10,12,20}` is the dice to roll

Example

```
http://aleajactarest.herokuapp.com/api/dices/roll/2/d20
```

Will result in:

```json
{
    "dice": "d20",
    "result": 32,
    "operator": "+",
    "modifier": 0,
    "partials": [
        19,
        13
    ]
}
```

#### Rolls n dices with a modifier

```
http://aleajactarest.herokuapp.com/api/dices/roll/{n}/d{4,6,8,10,12,20}/{+,-,*,÷}/{m}
```

Where:
 * `n` is the number of times a dice is rolled
 * `d{4,6,8,10,12,20}` is the dice to roll
 * `{+,-,*,÷}` is the mathematical operation to be performed
 * `m` is the modifier of the operation

Example

```
http://aleajactarest.herokuapp.com/api/dices/roll/10/d20/*/5
```

Will result in:

```json
{
    "dice": "d20",
    "result": 660,
    "operator": "*",
    "modifier": 5,
    "partials": [
        18,
        19,
        14,
        15,
        10,
        13,
        17,
        15,
        2,
        9
    ]
}
```

#### Rolls with [Dice Notation](https://en.wikipedia.org/wiki/Dice_notation)

```
http://aleajactarest.herokuapp.com/api/dices/roll/notation/{diceNotation}
```

Where:
 * `diceNotation` is the dice notation

Example

```
http://aleajactarest.herokuapp.com/api/dices/roll/notation/10d20-5
```

Will result in:

```json
{
    "dice": "d20",
    "result": 109,
    "operator": "-",
    "modifier": 5,
    "partials": [
        16,
        3,
        12,
        15,
        16,
        12,
        7,
        1,
        18,
        14
    ]
}
```

### Dice Cup Roll

Rolls several dices of different types using the dice notation separated by comma (,).

```
http://aleajactarest.herokuapp.com/api/dices/cup/{diceNotation1,diceNotation2,...,diceNotationN}
```

Where:
 * `{diceNotation1,diceNotation2,...,diceNotationN}` is the list of dices in the Dice Notation format

Example

```
http://aleajactarest.herokuapp.com/api/dices/cup/2d4+7,3d6*5,5d8-4,4d10÷3,6d12,1d20
```

Will result in:

```json
{
    "masterResult": 114,
    "dices": [
        {
            "dice": "d4",
            "result": 15,
            "operator": "+",
            "modifier": 7,
            "partials": [
                4,
                4
            ]
        },
        {
            "dice": "d6",
            "result": 40,
            "operator": "*",
            "modifier": 5,
            "partials": [
                4,
                1,
                3
            ]
        },
        {
            "dice": "d8",
            "result": 11,
            "operator": "-",
            "modifier": 4,
            "partials": [
                1,
                1,
                4,
                4,
                5
            ]
        },
        {
            "dice": "d10",
            "result": 6,
            "operator": "÷",
            "modifier": 3,
            "partials": [
                4,
                5,
                4,
                5
            ]
        },
        {
            "dice": "d12",
            "result": 41,
            "operator": "+",
            "modifier": 0,
            "partials": [
                12,
                5,
                2,
                10,
                3,
                9
            ]
        },
        {
            "dice": "d20",
            "result": 1,
            "operator": "+",
            "modifier": 0,
            "partials": [
                1
            ]
        }
    ]
}
```

### Custom Dice Roll

Rolls a custom dice with a template dice and custom values separated by comma (,).

```
http://aleajactarest.herokuapp.com/api/dices/custom/{template}/{values}
```

Where:
 * `template` must be one of `d{4,6,8,10,12,20}` 
 * `values` a list of values separated by comma

Example

```
http://aleajactarest.herokuapp.com/api/dices/custom/d6/PIRATES,NINJAS,ZOMBIES,ROBOTS,ALIENS,VAMPIRES
```

Will result in:

```json
{
    "template": "d6",
    "result": "ZOMBIES",
    "faces": [
        "PIRATES",
        "NINJAS",
        "ZOMBIES",
        "ROBOTS",
        "ALIENS",
        "VAMPIRES"
    ]
}
```

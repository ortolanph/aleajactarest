# Alea, Jacta, REST!

## About

Dice rolling with REST interface.

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

```(json5)
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

Example

```
http://aleajactarest.herokuapp.com/api/dices/roll/2/d20
```

Will result in:

```(json5)
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

Example

```
http://aleajactarest.herokuapp.com/api/dices/roll/10/d20/*/5
```

Will result in:

```
```

#### Rolls with [Dice Notation](https://en.wikipedia.org/wiki/Dice_notation)*

```
/roll/dice/{diceNotation}
```

Example

```
/roll/dice/10d20-5
```

Will result in:

```
{
    "dice": "d20",
    "result": 111,
    "operator": "-",
    "modifier": 5,
    "partials":
        [
            3,11,2,5,20,15,15,6,19,20
        ]
}
```

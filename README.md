Alea, Jacta, REST!
==================

About
-----

Dice rolling with REST interface.

Interface
---------

*Rolls one dice*

```
/roll/d{4,6,8,10,12,20}
```

Example:

```
/roll/d20
```

Will result in:

```
{
    "dice": "d20",
    "result": 4,
    "operator": "+",
    "modifier": 0,
    "partials":
        [
            4
        ]
}
```

*Rolls n dices*

```
/roll/n/d{4,6,8,10,12,20}
```

Example

```
/roll/2/d20
```

Will result in:

```
{
    "dice": "d20",
    "result": 14,
    "operator": "+",
    "modifier": 0,
    "partials":
        [
            4,10
        ]
}
```

*Rolls n dices with a modifier*

```
/roll/n/d{4,6,8,10,12,20}/{+,-,*,÷}/m
```

Example

```
/roll/10/d20/*/5
```

Will result in:

```
{
    "dice": "d20",
    "result": 505,
    "operator": "*",
    "modifier": 5,
    "partials":
        [
            10,15,11,16,18,6,3,2,9,11
        ]
}
```

*Rolls with [Dice Notation](https://en.wikipedia.org/wiki/Dice_notation)*

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

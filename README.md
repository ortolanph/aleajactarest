Alea, Jacta, REST!
==================

About
-----

Dice rolling with REST interface.

Interface
---------

*Rolls one dice*

```
/d{4,6,8,10,12,20}
```

Example:

```
/d20
```

Will result in:

```
{
    "dice":"d20",
    "modificator":0,
    "result":7,
    "partials":[7]
}
```

*Rolls n dices*

```
/n/d{4,6,8,10,12,20}
```

Example

```
/2/d20
```

Will result in:

```
{
    "dice":"d20",
    "modificator":0,
    "result":29,
    "partials":[10,19]
}
```

*Rolls n dices and put a modificator*

```
/n/d{4,6,8,10,12,20}/m
```

Example

```
/10/d20/5
```

Will result in:

```
{
    "dice":"d20",
    "modificator":5,
    "result":107,
    "partials":[8,1,12,16,9,5,17,17,9,8]
}
```
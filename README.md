Alea, Jacta, REST!
==================

About
-----

Dice rolling with REST interface.

Interface
---------

1. Rolls one dice

```
/d{4,6,8,10,12,20}
```

Example:

```
/d20
```

Will result in:

```
pending
```

2. Rolls n dices

```
/n/d{4,6,8,10,12,20}
```

Example

```
/2/d20
```

Will result in:

```
pending
```

3. Rolls n dices and put a modificator

```
/n/d{4,6,8,10,12,20}/mod
```

Example

```
/2/d20/2
```

Will result in:

```
pending
```
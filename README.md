># ***You are on the solution branch.***
> Make sure to try solving it yourself first and check out the example solution afterwards. The provided solution is of course only an example of what your solution could look like.

# Roman Numerals Kata

A coding kata that focuses on **design** and **TDD**.

## Design Goals

When implementing a conversion between decimal and roman numbers, it seems like an obvious choice to represent roman numbers as strings and write static methods to convert between integers and strings. The result is procedural code (despite using an "object-oriented language") and code smells known as [*primitive obsession*](https://refactoring.guru/smells/primitive-obsession), [*stringly typed*](https://wiki.c2.com/?StringlyTyped) and [*feature envy*](https://refactoring.guru/smells/feature-envy). Surely you've seen it many times, and chances are you've produced it: High-level business objects exposing low-level implementation details by representing every lower-level concept as a primitive type or string, and strong coupling to static methods in an attempt to "reduce duplication" ([*DRY*](https://wiki.c2.com/?DontRepeatYourself)). It's probably one of the most common code smells, and can quickly turn into a maintainability problem (e. g. imagine having to replace an `int` with a `long` in dozens of occurrences through every architecture layer).

With test-driven development (TDD), the problem seems to be fixed ... at first glance. Since we write the test first and design the API before implementing it, we probably don't use a static method now; instead, we create an object to do the conversion, so ... object-oriented, right? A typical implementation could look something like this:

```
var converter = new RomanNumeralConverter();
String roman42 = converter.toRoman(42); // Returns "XLII".
```

At second glance, this still demonstrates procedural thinking. An object on the heap does not automatically lead to object-oriented code. This kata intends to raise awareness of a common missing link in the abstraction levels of software design: The level just above primitive types and strings, where simple datatypes are represented as objects that do their respective operations themselves. These types are called [*value objects*](https://wiki.c2.com/?ValueObject). They lead to more flexible, testable, and self-documenting code, and they prevent disasters when implementation details change.

## Kata Requirements

### Design Constraints

- Roman numerals are represented as objects, not strings.
- They are immutable. Every operation that results in a new value creates a new object.

### Functional Requirements

**Must have**

1. Numbers from 1 to 5 (I, II, III, IV, V) are supported.
1. Can be created from an integer value.
1. `toString()` returns the string representation of the roman numeral (e. g. "IV").
1. `toInt()` returns the integer representation.
1. `equals()` and `hashCode()`.
1. Two roman numerals can be added together, using an instance method or operator overload, resulting in a new value and thus a new object.
1. A roman numeral can be parsed from a string (e. g. "IV").

**Nice to have**

1. Numbers from 1 to 3999 are supported (see below for conversion rules). Other numbers are considered out of range and errors are thrown accordingly.

**Optional brain teasers**

1. What would be a way to optimize the conversion of all 3999 numbers for execution speed?
2. How can you optimize for a small memory footprint, assuming that numbers from 1 to 12 occur disproportionally often?

### Conversion Rules

We use the standard form [as explained on Wikipedia](https://en.wikipedia.org/wiki/Roman_numerals#Standard_form).

The following table exhaustively defines all numbers from 1 (I) to 3999 (MMMCMXCIX), which is the largest number that can be represented in the standard form.

Example: 2023 is created using 2 Thousands (MM) + 0 Hundreds + 2 Tens (XX) + 3 Units (III) = MMXXIII.

|   | Thousands | Hundreds | Tens | Units  |
|:-:|:---------:|:--------:|:----:|:------:|
| 1 | M         | C        | X    | I      |
| 2 | MM        | CC       | XX   | II     |
| 3 | MMM       | CCC      | XXX  | III    |
| 4 |           | CD       | XL   | IV     |
| 5 |           | D        | L    | V      |
| 6 |           | DC       | LX   | VI     |
| 7 |           | DCC      | LXX  | VII    |
| 8 |           | DCCC     | LXXX | VIII   |
| 9 |           | CM       | XC   | IX     |

## Tips

- When coming from a mental model where roman numerals are strings, one might think of these value objects as "wrappers" around strings. Instead, try to think about the externally observable behavior of the object separately from the internal representation (this separation of mental models is also called "technical empathy"). Remember: We are creating a numeric type here!
- There might be a way to greatly simplify the conversion logic, compared to the [naive algorithm](https://stackoverflow.com/a/5700793/3726133) using addition and subtraction. Can we somehow represent the above table in code and use it in our conversion algorithm?

___

© 2023 Raimund Krämer - Use with attribution.

Links to third party sites are included for convenience only and I am not responsible for their contents.

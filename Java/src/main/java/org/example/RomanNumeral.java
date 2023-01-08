package org.example;

import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;

public class RomanNumeral {

    private final int value;

    public RomanNumeral(int value) {
        if (value < 1 || value > 3999) {
            throw new IllegalArgumentException(
                    String.format("%d is out of range for roman numerals.", value));
        }
        this.value = value;
    }

    public RomanNumeral(String roman) {
        value = parse(roman);
    }

    public RomanNumeral plus(RomanNumeral toAdd) {
        return new RomanNumeral(this.value + toAdd.value);
    }

    public int toInt() {
        return value;
    }

    @Override
    public String toString() {
        return convertToRoman(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RomanNumeral that = (RomanNumeral) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private int parse(String roman) {
        final var thousandsPart = highestMatchingPrefixOrNull(roman, allRomanThousands);
        final var thousandsValue = toThousandsValueOrZero(thousandsPart);
        final var remainingRomanWithoutThousands = roman.replaceFirst(
                thousandsPart != null ? thousandsPart : "",
                ""
        );

        final var hundredsPart = highestMatchingPrefixOrNull(remainingRomanWithoutThousands, allRomanHundreds);
        final var hundredsValue = toHundredsValueOrZero(hundredsPart);
        final var remainingRomanWithoutHundreds = remainingRomanWithoutThousands.replaceFirst(
                hundredsPart != null ? hundredsPart : "",
                ""
        );

        final var tensPart = highestMatchingPrefixOrNull(remainingRomanWithoutHundreds, allRomanTens);
        final var tensValue = toTensValueOrZero(tensPart);
        final var remainingRomanWithoutTens = remainingRomanWithoutHundreds.replaceFirst(
                tensPart != null ? tensPart : "",
                ""
        );

        final var unitsPart = highestMatchingPrefixOrNull(remainingRomanWithoutTens, allRomanUnits);
        final var unitsValue = toUnitsValueOrZero(unitsPart);
        final var remainingRomanWithoutUnits = remainingRomanWithoutTens.replaceFirst(
                unitsPart != null ? unitsPart : "",
                ""
        );

        final var parsedValue = thousandsValue + hundredsValue + tensValue + unitsValue;

        if (parsedValue == 0 || !remainingRomanWithoutUnits.isEmpty()) {
            throw new NumberFormatException(
                    String.format("%s is not a supported roman numeral.", roman));
        }

        return parsedValue;
    }

    private String highestMatchingPrefixOrNull(String roman, List<String> positionalPrefixes) {
        // We match the last, i.e. the highest prefix for the current decimal position,
        // since higher roman "digits" can contain lower ones (e. g. XC contains X),
        // which would otherwise lead to a false-positive matching of X.
        BinaryOperator<String> reduceToLast = (first, second) -> second;

        return positionalPrefixes
                .stream()
                .filter(roman::startsWith)
                .reduce(reduceToLast)
                .orElse(null);
    }

    private int toThousandsValueOrZero(String romanThousandsPartOrNull) {
        if (romanThousandsPartOrNull == null) {
            return 0;
        }

        final var index = allRomanThousands.indexOf(romanThousandsPartOrNull);
        if (index < 0) {
            throw new NumberFormatException();
        }

        return 1000 * (index + 1);
    }

    private int toHundredsValueOrZero(String romanHundredsPartOrNull) {
        if (romanHundredsPartOrNull == null) {
            return 0;
        }

        final var index = allRomanHundreds.indexOf(romanHundredsPartOrNull);
        if (index < 0) {
            throw new NumberFormatException();
        }

        return 100 * (index + 1);
    }

    private int toTensValueOrZero(String romanTensPartOrNull) {
        if (romanTensPartOrNull == null) {
            return 0;
        }

        final var index = allRomanTens.indexOf(romanTensPartOrNull);
        if (index < 0) {
            throw new NumberFormatException();
        }

        return 10 * (index + 1);
    }

    private int toUnitsValueOrZero(String romanUnitsPartOrNull) {
        if (romanUnitsPartOrNull == null) {
            return 0;
        }

        final var index = allRomanUnits.indexOf(romanUnitsPartOrNull);
        if (index < 0) {
            throw new NumberFormatException();
        }

        return index + 1;
    }

    private String convertToRoman(int n) {
        // Offset for zero-based indices, e.g. "I" is at position 0.
        final var indexOffset = 1;

        final var thousandsIndex = n / 1000 - indexOffset;
        final var romanThousands = getOrElse(allRomanThousands, thousandsIndex, "");

        final var hundredsIndex = (n % 1000) / 100 - indexOffset;
        final var romanHundreds = getOrElse(allRomanHundreds, hundredsIndex, "");

        final var tensIndex = (n % 100) / 10 - indexOffset;
        final var romanTens = getOrElse(allRomanTens, tensIndex, "");

        final var unitsIndex = n % 10 - indexOffset;
        final var romanUnits = getOrElse(allRomanUnits, unitsIndex, "");

        return romanThousands + romanHundreds + romanTens + romanUnits;
    }

    private <T> T getOrElse(List<T> list, int index, T defaultValue) {
        if (index > list.size() - 1) {
            throw new IndexOutOfBoundsException(index);
        } else if (index < 0) {
            return defaultValue;
        } else {
            return list.get(index);
        }
    }

    private final List<String> allRomanThousands = List.of(
            "M",
            "MM",
            "MMM"
    );

    private final List<String> allRomanHundreds = List.of(
            "C",
            "CC",
            "CCC",
            "CD",
            "D",
            "DC",
            "DCC",
            "DCCC",
            "CM"
    );

    private final List<String> allRomanTens = List.of(
            "X",
            "XX",
            "XXX",
            "XL",
            "L",
            "LX",
            "LXX",
            "LXXX",
            "XC"
    );

    private final List<String> allRomanUnits = List.of(
            "I",
            "II",
            "III",
            "IV",
            "V",
            "VI",
            "VII",
            "VIII",
            "IX"
    );
}

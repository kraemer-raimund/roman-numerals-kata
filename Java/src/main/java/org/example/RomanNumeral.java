package org.example;

import java.util.Objects;

public class RomanNumeral {

    private final int value;

    public RomanNumeral(int value) {
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

    private String convertToRoman(int n) {
        return switch (n) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            default -> throw new NumberFormatException(
                    String.format("%d is outside of the supported range of roman numerals.", value));
        };
    }

    private int parse(String roman) {
        if (roman.equals("I")) {
            return 1;
        } else if (roman.equals("II")) {
            return 2;
        } else if (roman.equals("III")) {
            return 3;
        } else if (roman.equals("IV")) {
            return 4;
        } else if (roman.equals("V")) {
            return 5;
        } else {
            throw new NumberFormatException(
                    String.format("%s is not a supported roman numeral.", roman));
        }
    }
}

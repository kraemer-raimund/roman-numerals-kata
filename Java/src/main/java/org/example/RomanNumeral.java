package org.example;

public class RomanNumeral {

    private final int value;

    public RomanNumeral(int value) {
        this.value = value;
    }

    public RomanNumeral(String roman) {
        if (roman.equals("I")) {
            value = 1;
        } else {
            throw new NumberFormatException(
                    String.format("%s is not a supported roman numeral.", roman));
        }
    }

    public RomanNumeral plus(RomanNumeral toAdd) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public int toInt() {
        return value;
    }

    @Override
    public String toString() {
        if (value == 1) {
            return "I";
        }
        throw new NumberFormatException(
                String.format("%d is outside of the supported range of roman numerals.", value));
    }
}

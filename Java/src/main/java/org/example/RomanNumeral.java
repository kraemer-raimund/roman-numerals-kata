package org.example;

public class RomanNumeral {

    private final int value;

    public RomanNumeral(int value) {
        this.value = value;
    }

    public RomanNumeral(String roman) {
        value = parse(roman);
    }

    public RomanNumeral plus(RomanNumeral toAdd) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public int toInt() {
        return value;
    }

    @Override
    public String toString() {
        return convertToRoman(value);
    }

    private String convertToRoman(int n) {
        if (n == 1) {
            return "I";
        }
        throw new NumberFormatException(
                String.format("%d is outside of the supported range of roman numerals.", value));
    }

    private int parse(String roman) {
        if (roman.equals("I")) {
            return  1;
        } else {
            throw new NumberFormatException(
                    String.format("%s is not a supported roman numeral.", roman));
        }
    }
}

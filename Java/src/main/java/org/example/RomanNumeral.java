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
        return new RomanNumeral(this.value + toAdd.value);
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
        } else if (n == 2) {
            return "II";
        } else if (n == 3) {
            return "III";
        } else if (n == 4) {
            return "IV";
        } else if (n == 5) {
            return "V";
        }
        throw new NumberFormatException(
                String.format("%d is outside of the supported range of roman numerals.", value));
    }

    private int parse(String roman) {
        if (roman.equals("I")) {
            return  1;
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

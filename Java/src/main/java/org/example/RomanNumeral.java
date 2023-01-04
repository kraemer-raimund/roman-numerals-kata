package org.example;

public class RomanNumeral {

    private final int value;

    public RomanNumeral(int value) {
        this.value = value;
    }

    public RomanNumeral(String roman) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public RomanNumeral plus(RomanNumeral toAdd) {
        throw new UnsupportedOperationException("Not yet implemented.");
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

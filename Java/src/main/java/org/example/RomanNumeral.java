package org.example;

import java.util.Objects;

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
        var remainingRoman = roman;
        var value = 0;

        final var thousandsRoman = thousandsPart(remainingRoman);
        value += parseThousands(thousandsRoman);
        remainingRoman = remainingRoman.replaceFirst(thousandsRoman, "");

        final var hundredsRoman = hundredsPart(remainingRoman);
        value += parseHundreds(hundredsRoman);
        remainingRoman = remainingRoman.replaceFirst(hundredsRoman, "");

        final var tensRoman = tensPart(remainingRoman);
        value += parseTens(tensRoman);
        remainingRoman = remainingRoman.replaceFirst(tensRoman, "");

        final var unitsRoman = unitsPart(remainingRoman);
        value += parseUnits(unitsRoman);
        remainingRoman = remainingRoman.replaceFirst(unitsRoman, "");

        if (value == 0 || !remainingRoman.isEmpty()) {
            throw new NumberFormatException(
                    String.format("%s is not a supported roman numeral.", roman));
        }

        return value;
    }

    private String thousandsPart(String roman) {
        if (roman.startsWith("MMM")) {
            return "MMM";
        } else if (roman.startsWith("MM")) {
            return "MM";
        } else if (roman.startsWith("M")) {
            return "M";
        } else {
            return "";
        }
    }

    private int parseThousands(String romanThousands) {
        return switch (romanThousands) {
            case "M" -> 1000;
            case "MM" -> 2000;
            case "MMM" -> 3000;
            default -> 0;
        };
    }

    private String hundredsPart(String roman) {
        if (roman.startsWith("CM")) {
            return "CM";
        } else if (roman.startsWith("DCCC")) {
            return "DCCC";
        } else if (roman.startsWith("DCC")) {
            return "DCC";
        } else if (roman.startsWith("DC")) {
            return "DC";
        } else if (roman.startsWith("D")) {
            return "D";
        } else if (roman.startsWith("CD")) {
            return "CD";
        } else if (roman.startsWith("CCC")) {
            return "CCC";
        } else if (roman.startsWith("CC")) {
            return "CC";
        } else if (roman.startsWith("C")) {
            return "C";
        } else {
            return "";
        }
    }

    private int parseHundreds(String romanHundreds) {
        return switch (romanHundreds) {
            case "C" -> 100;
            case "CC" -> 200;
            case "CCC" -> 300;
            case "CD" -> 400;
            case "D" -> 500;
            case "DC" -> 600;
            case "DCC" -> 700;
            case "DCCC" -> 800;
            case "CM" -> 900;
            default -> 0;
        };
    }

    private String tensPart(String roman) {
        if (roman.startsWith("XC")) {
            return "XC";
        } else if (roman.startsWith("LXXX")) {
            return "LXXX";
        } else if (roman.startsWith("LXX")) {
            return "LXX";
        } else if (roman.startsWith("LX")) {
            return "LX";
        } else if (roman.startsWith("L")) {
            return "L";
        } else if (roman.startsWith("XL")) {
            return "XL";
        } else if (roman.startsWith("XXX")) {
            return "XXX";
        } else if (roman.startsWith("XX")) {
            return "XX";
        } else if (roman.startsWith("X")) {
            return "X";
        } else {
            return "";
        }
    }

    private int parseTens(String romanTens) {
        return switch (romanTens) {
            case "X" -> 10;
            case "XX" -> 20;
            case "XXX" -> 30;
            case "XL" -> 40;
            case "L" -> 50;
            case "LX" -> 60;
            case "LXX" -> 70;
            case "LXXX" -> 80;
            case "XC" -> 90;
            default -> 0;
        };
    }

    private String unitsPart(String roman) {
        if (roman.startsWith("IX")) {
            return "IX";
        } else if (roman.startsWith("VIII")) {
            return "VIII";
        } else if (roman.startsWith("VII")) {
            return "VII";
        } else if (roman.startsWith("VI")) {
            return "VI";
        } else if (roman.startsWith("V")) {
            return "V";
        } else if (roman.startsWith("IV")) {
            return "IV";
        } else if (roman.startsWith("III")) {
            return "III";
        } else if (roman.startsWith("II")) {
            return "II";
        } else if (roman.startsWith("I")) {
            return "I";
        } else {
            return "";
        }
    }

    private int parseUnits(String romanUnits) {
        return switch (romanUnits) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            default -> 0;
        };
    }
}

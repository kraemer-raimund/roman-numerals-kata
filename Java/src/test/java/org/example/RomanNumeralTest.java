package org.example;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class RomanNumeralTest {

    @Test
    void romanStringCanBeConvertedToInt() {
        var expectedIntValueByRomanNumeral = Map.of(
                "I", 1,
                "XLII", 42,
                "XCIX", 99,
                "C", 100,
                "CXI", 111,
                "CMXCIX", 999,
                "MMXXIII", 2023,
                "MMMCMXCIX", 3999
        );

        expectedIntValueByRomanNumeral.forEach((roman, intValue) -> {
            assertThat(new RomanNumeral(roman).toInt()).isEqualTo(intValue);
        });
    }

    @Test
    void parsingInvalidStringThrows() {
        assertThatExceptionOfType(NumberFormatException.class)
                .isThrownBy(() -> new RomanNumeral("XXXX"));
    }

    @Test
    void intCanBeConvertedToRomanString() {
        var expectedRomanNumeralByIntValue = Map.of(
                1, "I"
        );

        expectedRomanNumeralByIntValue.forEach((intValue, roman) -> {
            assertThat(new RomanNumeral(intValue).toString()).isEqualTo(roman);
        });
    }

    @Test
    void valueMustBeInRange() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new RomanNumeral(-100));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new RomanNumeral(0));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new RomanNumeral(4000));

        assertThatNoException().isThrownBy(() -> new RomanNumeral(1));
        assertThatNoException().isThrownBy(() -> new RomanNumeral(3999));
    }

    @Test
    void addingTwoNumeralsYieldsTheirSum() {
        var roman2 = new RomanNumeral(2);
        var roman3 = new RomanNumeral("III");

        var romanSum = roman2.plus(roman3);

        assertThat(romanSum.toString()).isEqualTo("V");
        assertThat(romanSum.toInt()).isEqualTo(5);
    }

    @Test
    void equalsAndHashCode() {
        var roman1a = new RomanNumeral(1);
        var roman1b = new RomanNumeral("I");
        var roman2 = new RomanNumeral(2);

        // Note:
        // Two equal objects must have the same hash code, but two unequal objects
        // are *NOT* required to have different hash codes!
        assertThat(roman1a.hashCode())
                .isEqualTo(roman1b.hashCode());
        assertThatObject(roman1a)
                .isEqualTo(roman1a)
                .isEqualTo(roman1b)
                .isNotEqualTo(roman2);
    }
}

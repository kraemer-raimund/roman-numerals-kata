package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;

class RomanNumeralTest {

    @Test
    void romanStringCanBeConvertedToInt() {
        var roman1 = new RomanNumeral("I");
        var intValue = roman1.toInt();
        assertThat(intValue).isEqualTo(1);
    }

    @Test
    void intCanBeConvertedToRomanString() {
        var roman1 = new RomanNumeral(1);
        var romanString = roman1.toString();
        assertThat(romanString).isEqualTo("I");
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

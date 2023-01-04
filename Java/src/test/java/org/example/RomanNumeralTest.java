package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}

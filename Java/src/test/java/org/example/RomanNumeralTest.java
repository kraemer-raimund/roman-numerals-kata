package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RomanNumeralTest {

    @Test
    void intCanBeConvertedToRomanString() {
        var roman1 = new RomanNumeral(1);
        var romanString = roman1.toString();
        assertThat(romanString).isEqualTo("I");
    }

    @Test
    @Disabled("This test acts as an example, but does not make sense " +
            "until a much later TDD iteration.")
    void addingTwoNumeralsYieldsTheirSum() {
        var roman2 = new RomanNumeral(2);
        var roman3 = new RomanNumeral("III");

        var romanSum = roman2.plus(roman3);

        assertThat(romanSum.toString()).isEqualTo("V");
    }
}

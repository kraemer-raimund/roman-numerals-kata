package org.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RomanNumeralTest {

    @Test
    fun romanStringCanBeConvertedToInt() {
        val roman1 = RomanNumeral("I");
        val intValue = roman1.toInt();
        assertThat(intValue).isEqualTo(1);
    }

    @Test
    fun intCanBeConvertedToRomanString() {
        val roman1 = RomanNumeral(1)
        val romanString = roman1.toString()
        assertThat(romanString).isEqualTo("I")
    }
}

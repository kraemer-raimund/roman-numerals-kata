package org.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RomanNumeralTest {

    @Test
    fun addingTwoNumeralsYieldsTheirSum() {
        val roman2 = RomanNumeral(2)
        val roman3 = RomanNumeral("III")

        val romanSum = roman2 + roman3

        assertThat(romanSum.toString()).isEqualTo("V")
    }
}

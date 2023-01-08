package org.example

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class RomanNumeralTest {

    @Test
    fun romanStringCanBeConvertedToInt() {
        val expectedIntValueByRomanNumeral = mapOf(
            Pair("I", 1),
            Pair("XLII", 42),
            Pair("XCIX", 99),
            Pair("C", 100),
            Pair("CXI", 111),
            Pair("CMXCIX", 999),
        )

        expectedIntValueByRomanNumeral.forEach { (roman, expectedValue) ->
            val actualValue = RomanNumeral(roman).toInt()
            assertThat(actualValue).isEqualTo(expectedValue);
        }
    }

    @Test
    fun parsingInvalidStringThrows() {
        assertThatExceptionOfType(NumberFormatException::class.java)
            .isThrownBy { RomanNumeral("XXXX") }
    }

    @Test
    fun intCanBeConvertedToRomanString() {
        val roman1 = RomanNumeral(1)
        val romanString = roman1.toString()
        assertThat(romanString).isEqualTo("I")
    }

    @Test
    fun addingTwoNumeralsYieldsTheirSum() {
        val roman2 = RomanNumeral(2)
        val roman3 = RomanNumeral("III")
        val sum = roman2 + roman3
        assertThat(sum.toString()).isEqualTo("V")
        assertThat(sum.toInt()).isEqualTo(5)
    }

    @Test
    fun equalsAndHashCode() {
        val roman1a = RomanNumeral(1)
        val roman1b = RomanNumeral("I")
        val roman2 = RomanNumeral(2)

        // Note:
        // Two equal objects must have the same hash code, but two unequal objects
        // are *NOT* required to have different hash codes!
        assertThat(roman1a.hashCode())
            .isEqualTo(roman1b.hashCode())
        Assertions.assertThatObject(roman1a)
            .isEqualTo(roman1a)
            .isEqualTo(roman1b)
            .isNotEqualTo(roman2)
    }
}

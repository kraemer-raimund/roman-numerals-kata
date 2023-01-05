package org.example

import java.lang.NumberFormatException

class RomanNumeral {

    private val value: Int

    constructor(value: Int) {
        this.value = value
    }

    constructor(roman: String) {
        value = parse(roman)
    }

    operator fun plus(toAdd: RomanNumeral): RomanNumeral {
        return RomanNumeral(value + toAdd.value)
    }

    fun toInt(): Int {
        return value;
    }

    override fun toString(): String {
        return when (value) {
            1 -> "I"
            2 -> "II"
            3 -> "III"
            4 -> "IV"
            5 -> "V"
            else -> throw NumberFormatException()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as RomanNumeral
        if (value != other.value) return false
        return true
    }

    override fun hashCode(): Int = value

    private fun parse(roman: String): Int {
        return when (roman) {
            "I" -> 1
            "II" -> 2
            "III" -> 3
            "IV" -> 4
            "V" -> 5
            else -> throw NumberFormatException()
        }
    }
}

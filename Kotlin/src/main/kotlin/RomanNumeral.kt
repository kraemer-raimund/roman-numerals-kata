package org.example

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
        val tensPart = highestMatchingPrefix(roman, allRomanTens)
        val tensValue = when {
            tensPart != null -> toTensValue(tensPart)
            else -> 0
        }

        val unitsPart = highestMatchingPrefix(
            roman.removePrefix(tensPart ?: ""),
            allRomanUnits
        )
        val unitsValue = when {
            unitsPart != null -> toUnitsValue(unitsPart)
            else -> 0
        }

        return tensValue + unitsValue
    }

    private fun highestMatchingPrefix(
        roman: String,
        positionalPrefixes: List<String>
    ): String? {
        return positionalPrefixes.lastOrNull { roman.startsWith(it) }
    }

    private fun toTensValue(romanTensPart: String): Int {
        val index = allRomanTens.indexOf(romanTensPart)
        if (index < 0) {
            throw NumberFormatException()
        }
        return 10 * (index + 1)
    }

    private fun toUnitsValue(romanUnitsPart: String): Int {
        val index = allRomanUnits.indexOf(romanUnitsPart)
        if (index < 0) {
            throw NumberFormatException()
        }
        return 1 * (index + 1)
    }

    private val allRomanTens: List<String> =
        listOf(
            "X",
            "XX",
            "XXX",
            "XL",
            "L",
            "LX",
            "LXX",
            "LXXX",
            "XC"
        )

    private val allRomanUnits: List<String> =
        listOf(
            "I",
            "II",
            "III",
            "IV",
            "V",
            "VI",
            "VII",
            "VIII",
            "IX"
        )
}

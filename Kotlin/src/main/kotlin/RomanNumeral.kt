package org.example

class RomanNumeral {

    private val value: Int

    constructor(value: Int) {
        require(value in 1..3999) {
            String.format("%d is out of range for roman numerals.", value)
        }
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
        val thousandsPart = highestMatchingPrefix(roman, allRomanThousands)
        val thousandsValue = toThousandsValueOrNull(thousandsPart) ?: 0
        val remainingRomanWithoutThousands = roman.removePrefix(thousandsPart ?: "")

        val hundredsPart = highestMatchingPrefix(remainingRomanWithoutThousands, allRomanHundreds)
        val hundredsValue = toHundredsValueOrNull(hundredsPart) ?: 0
        val remainingRomanWithoutHundreds = remainingRomanWithoutThousands.removePrefix(hundredsPart ?: "")

        val tensPart = highestMatchingPrefix(remainingRomanWithoutHundreds, allRomanTens)
        val tensValue = toTensValueOrNull(tensPart) ?: 0
        val remainingRomanWithoutTens = remainingRomanWithoutHundreds.removePrefix(tensPart ?: "")

        val unitsPart = highestMatchingPrefix(remainingRomanWithoutTens, allRomanUnits)
        val unitsValue = toUnitsValueOrNull(unitsPart) ?: 0
        val remainingRomanWithoutUnits = remainingRomanWithoutTens.removePrefix(unitsPart ?: "")

        if (remainingRomanWithoutUnits.isNotBlank()) {
            throw NumberFormatException("`$roman` is not a valid roman numeral.")
        }

        return thousandsValue + hundredsValue + tensValue + unitsValue
    }

    private fun highestMatchingPrefix(
        roman: String,
        positionalPrefixes: List<String>
    ): String? {
        // We match the last, i. e. highest prefix for the current decimal position,
        // since higher roman "digits" can contain lower ones (e. g. XC contains X),
        // which would otherwise lead to a false-positive matching of X.
        return positionalPrefixes.lastOrNull { roman.startsWith(it) }
    }

    private fun toThousandsValueOrNull(romanThousandsPartOrNull: String?): Int? {
        romanThousandsPartOrNull ?: return null
        val index = allRomanThousands.indexOf(romanThousandsPartOrNull)
        if (index < 0) {
            throw NumberFormatException()
        }
        return 1000 * (index + 1)
    }

    private fun toHundredsValueOrNull(romanHundredsPartOrNull: String?): Int? {
        romanHundredsPartOrNull ?: return null
        val index = allRomanHundreds.indexOf(romanHundredsPartOrNull)
        if (index < 0) {
            throw NumberFormatException()
        }
        return 100 * (index + 1)
    }

    private fun toTensValueOrNull(romanTensPartOrNull: String?): Int? {
        romanTensPartOrNull ?: return null
        val index = allRomanTens.indexOf(romanTensPartOrNull)
        if (index < 0) {
            throw NumberFormatException()
        }
        return 10 * (index + 1)
    }

    private fun toUnitsValueOrNull(romanUnitsPartOrNull: String?): Int? {
        romanUnitsPartOrNull ?: return null
        val index = allRomanUnits.indexOf(romanUnitsPartOrNull)
        if (index < 0) {
            throw NumberFormatException()
        }
        return 1 * (index + 1)
    }

    private val allRomanThousands: List<String> =
        listOf(
            "M",
            "MM",
            "MMM"
        )

    private val allRomanHundreds: List<String> =
        listOf(
            "C",
            "CC",
            "CCC",
            "CD",
            "D",
            "DC",
            "DCC",
            "DCCC",
            "CM"
        )

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

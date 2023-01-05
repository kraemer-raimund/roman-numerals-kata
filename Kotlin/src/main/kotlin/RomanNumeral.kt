package org.example

class RomanNumeral(private val value: Int) {

    constructor(roman: String) : this(1)

    fun toInt(): Int {
        return 1;
    }

    override fun toString(): String {
        return "I";
    }
}

import org.junit.Test
import kotlin.test.assertEquals

// kata for converting an arabic integer to roman numerals
// see https://www.youtube.com/watch?time_continue=953&v=vX-Yym7166Y
// where it is done in ruby

private val constants: Map<Int, String> = hashMapOf(
        1 to "I",
        4 to "IV",
        5 to "V",
        9 to "IX",
        10 to "X",
        40 to "XL",
        50 to "L",
        90 to "XC",
        100 to "C",
        400 to "CD",
        500 to "D",
        900 to "CM",
        1000 to "M")

fun convert(arabic: Int): String? {
    if (arabic < 0 || arabic > 3999)
        throw IllegalArgumentException()

    if (arabic == 0)
        return ""

    var roman: String? = ""
    var step: Int = arabic

    while (step > 0) {
        if (constants.containsKey(step)) {
            roman = constants[step]
            break
        }
        step--
    }
    return roman + convert(arabic - step)
}

// tests have to be inside a class for junit to pick them up
class RomanNumerals {

    @Test
    fun zero() {
        assertEquals("", convert(0))
    }

    @Test
    fun numbersWithTheirOwnNumeral() {
        assertEquals("I", convert(1))
        assertEquals("V", convert(5))
        assertEquals("IX", convert(9))
        assertEquals("X", convert(10))
        assertEquals("L", convert(50))
        assertEquals("C", convert(100))
    }

    @Test
    fun numbersWhichHaveToBeCalculated() {
        assertEquals("VI", convert(6))
        assertEquals("VIII", convert(8))
        assertEquals("XCIX", convert(99))
        assertEquals("MMMCDXCVII", convert(3497))
    }

}


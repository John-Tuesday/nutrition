package io.github.john.tuesday.nutrition

import io.github.john.tuesday.measurement.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PortionUnitTest {
    @Test
    fun operatorsMismatchTest() {
        // plus
        assertFailsWith<MismatchPortionError> { MassPortion(1.grams) + VolumePortion(1.liters) }
        assertFailsWith<MismatchPortionError> { VolumePortion(1.liters) + MassPortion(1.grams) }
        assertFailsWith<MismatchPortionError> { Portion(1.grams) + Portion(1.liters) }
        assertFailsWith<MismatchPortionError> { Portion(1.liters) + Portion(1.grams) }
        // minus
        assertFailsWith<MismatchPortionError> { MassPortion(1.grams) - VolumePortion(1.liters) }
        assertFailsWith<MismatchPortionError> { VolumePortion(1.liters) - MassPortion(1.grams) }
        assertFailsWith<MismatchPortionError> { Portion(1.grams) - Portion(1.liters) }
        assertFailsWith<MismatchPortionError> { Portion(1.liters) - Portion(1.grams) }
        // div
        assertFailsWith<MismatchPortionError> { MassPortion(1.grams) / VolumePortion(1.liters) }
        assertFailsWith<MismatchPortionError> { VolumePortion(1.liters) / MassPortion(1.grams) }
        assertFailsWith<MismatchPortionError> { Portion(1.grams) / Portion(1.liters) }
        assertFailsWith<MismatchPortionError> { Portion(1.liters) / Portion(1.grams) }
    }

    @Test
    fun operatorsSuccessTest() {
        val amount = 2.00
        val offset = 1.00
        // plus
        assertEquals(MassPortion(amount.grams), MassPortion((amount - offset).grams) + MassPortion(offset.grams))
        assertEquals(VolumePortion(amount.liters), VolumePortion((amount - offset).liters) + VolumePortion(offset.liters))
        assertEquals(Portion(amount.grams), Portion((amount - offset).grams) + Portion(offset.grams))
        assertEquals(Portion(amount.liters), Portion((amount - offset).liters) + Portion(offset.liters))
        // minus
        assertEquals(MassPortion(amount.grams), MassPortion((amount + offset).grams) - MassPortion(offset.grams))
        assertEquals(VolumePortion(amount.liters), VolumePortion((amount + offset).liters) - VolumePortion(offset.liters))
        assertEquals(Portion(amount.grams), Portion((amount + offset).grams) - Portion(offset.grams))
        assertEquals(Portion(amount.liters), Portion((amount + offset).liters) - Portion(offset.liters))
        // unary minus
        assertEquals(MassPortion(amount.grams), -(-MassPortion(amount.grams)))
        assertEquals(VolumePortion(amount.liters), -(-VolumePortion(amount.liters)))
        assertEquals(Portion(amount.liters), -(-Portion(amount.liters)))
        assertEquals(Portion(amount.grams), -(-Portion(amount.grams)))
    }

    @Test
    fun absoluteValueTest() {
        val amount = 2.00
        assertEquals(MassPortion(amount.grams), MassPortion(-amount.grams).absoluteValue)
        assertEquals(VolumePortion(amount.liters), VolumePortion(-amount.liters).absoluteValue)
        assertEquals(Portion(amount.grams), Portion(-amount.grams).absoluteValue)
        assertEquals(Portion(amount.liters), Portion(-amount.liters).absoluteValue)
    }

    @Test
    fun scaleOperatorsTest() {
        val amount = 2.00
        val scale = 2.00
        // times
        assertEquals(MassPortion(amount.grams), MassPortion(amount.grams / scale) * scale)
        assertEquals(MassPortion(amount.grams), scale * MassPortion(amount.grams / scale))
        assertEquals(VolumePortion(amount.liters), VolumePortion(amount.liters / scale) * scale)
        assertEquals(VolumePortion(amount.liters), scale * VolumePortion(amount.liters / scale))
        assertEquals(Portion(amount.grams), Portion(amount.grams / scale) * scale)
        assertEquals(Portion(amount.grams), scale * Portion(amount.grams / scale))
        assertEquals(Portion(amount.liters), Portion(amount.liters / scale) * scale)
        assertEquals(Portion(amount.liters), scale * Portion(amount.liters / scale))
        // div
        assertEquals(MassPortion(amount.grams), MassPortion(amount.grams * scale) / scale)
        assertEquals(VolumePortion(amount.liters), VolumePortion(amount.liters * scale) / scale)
        assertEquals(Portion(amount.grams), Portion(amount.grams * scale) / scale)
        assertEquals(Portion(amount.liters), Portion(amount.liters * scale) / scale)
    }

    @Test
    fun ratioOperatorTest() {
        val amount = 2.00
        fun check(scale: Double, amount: Double) {
            assertEquals(scale, MassPortion(amount.grams * scale) / MassPortion(amount.grams))
            assertEquals(scale, VolumePortion(amount.liters * scale) / VolumePortion(amount.liters))
            assertEquals(scale, Portion(amount.grams * scale) / Portion(amount.grams))
            assertEquals(scale, Portion(amount.liters * scale) / Portion(amount.liters))
        }
        check(scale = 1.00, amount = amount)
        check(scale = 2.00, amount = amount)
        check(scale = 0.50, amount = amount)
    }
}

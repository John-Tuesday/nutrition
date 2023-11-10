package io.github.john.tuesday.nutrition

import io.github.john.tuesday.measurement.inGrams
import io.github.john.tuesday.measurement.inKilocalories
import io.github.john.tuesday.measurement.inMilliliters
import kotlin.test.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull


private fun assertAllFilled(foodNutrition: FoodNutrition, assertNonZero: Boolean = false) {
    assertNotEquals(0.00, foodNutrition.foodEnergy.inKilocalories())
    assertNotEquals(0.00, foodNutrition.portion.fold({ it.inGrams() }, { it.inMilliliters() }))
    NutrientType.entries.onEach {
        val actual = foodNutrition[it]
        assertNotNull(actual, message = "Nutrient $it is null")
        if (assertNonZero)
            assertNotEquals(0.00, actual.inGrams(), message = "Expected nutrient $it not equal to 0.00, but found $actual")
    }
}

class NutrientSamplesUnitTest {

    @Test
    fun `fill test`() {
        assertAllFilled(NutritionSamples.filledNutritionA, assertNonZero = false)
        assertAllFilled(NutritionSamples.filledNonZeroNutritionA, assertNonZero = true)
        assertAllFilled(NutritionSamples.filledNutritionB, assertNonZero = false)
    }
}

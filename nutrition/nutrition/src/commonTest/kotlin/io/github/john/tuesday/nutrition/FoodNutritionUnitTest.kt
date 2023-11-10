package io.github.john.tuesday.nutrition

import io.github.john.tuesday.measurement.*
import kotlin.test.*

class FoodNutritionUnitTest {
    private val zero = FoodNutrition(
        portion = Portion(0.grams),
        foodEnergy = 0.kilocalories,
        nutrients = NutrientType.entries.associateWith { 0.grams }
    )
    private val oneBase = 1
    private val one = FoodNutrition(
        portion = Portion(oneBase.grams),
        foodEnergy = oneBase.kilocalories,
        nutrients = NutrientType.entries.associateWith { oneBase.grams }
    )
    private val twoBase = 2
    private val two = FoodNutrition(
        portion = Portion(twoBase.grams),
        foodEnergy = twoBase.kilocalories,
        nutrients = NutrientType.entries.associateWith { twoBase.grams }
    )
    private val nutritionMass = two
    private val nutritionVolume = two.mutate(portion = Portion(1.liters))

    @Test
    fun scaleToPortionTest() {
        assertEquals(one, one.scaleToPortion(one.portion))
        assertEquals(two, two.scaleToPortion(two.portion))
        assertEquals(one, one.scaleToPortion(two.portion).scaleToPortion(one.portion))
        assertEquals(two, one.scaleToPortion(two.portion))
        assertEquals(one, two.scaleToPortion(one.portion))

        assertFailsWith<MismatchPortionError> { nutritionMass.scaleToPortion(Portion(1.liters)) }
        assertFailsWith<MismatchPortionError> { nutritionVolume.scaleToPortion(Portion(1.grams)) }
    }

    @Test
    fun unaryMinusTest() {
        fun check(base: FoodNutrition) {
            val actual = -base

            assertEquals(base, -(-base))
            assertEquals(-base.portion, actual.portion)
            assertEquals(-base.foodEnergy, actual.foodEnergy)
            assertContentEquals(base.nutrients.keys, actual.nutrients.keys.asIterable())
            for ((type, mass) in base.nutrients)
                assertEquals(-mass, actual[type])
        }
        check(one)
        assertTrue {
            one.unaryMinus().nutrients.values.all { it == -oneBase.grams }
        }
    }

    @Test
    fun plusMinusFoodNutritionTest() {
        assertEquals(two, one + one)
        assertEquals(zero, one - one)
        assertEquals(one, two - one)
        assertEquals(one, one + one - one)
        assertFailsWith<MismatchPortionError> { nutritionMass + nutritionVolume }
        assertFailsWith<MismatchPortionError> { nutritionMass - nutritionVolume }
    }

    @Test
    fun plusMinusNutrientTest() {
        val nutrientType = NutrientType.Protein
        val offset = 3.grams
        val base = one.mutate(
            nutritionMap = one.nutrients.toMutableMap().apply { set(nutrientType, get(nutrientType)!! + offset) }.toMap()
        )
        assertEquals(base, one + nutrientType(offset))
        assertEquals(one, base - nutrientType(offset))
        assertEquals(base, base - nutrientType(offset) + nutrientType(offset))
    }
}

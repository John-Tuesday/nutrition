package io.github.john.tuesday.nutrition

import org.calamarfederal.physical.measurement.grams
import org.calamarfederal.physical.measurement.kilocalories
import org.calamarfederal.physical.measurement.milligrams

/**
 * [FoodNutrition] samples to be used in testing
 */
object NutritionSamples {
    val filledNutritionA get() = FoodNutrition(
    portion = Portion(100.grams),
    foodEnergy = 359.kilocalories,
        mapOf(
            NutrientType.Protein to 12.8.grams,
            NutrientType.TotalFat to 6.41.grams,
            NutrientType.TotalCarbohydrate to 74.4.grams,
            NutrientType.Fiber to 10.3.grams,
            NutrientType.Starch to 8.11.grams,
            NutrientType.Sugar to 5.13.grams,
            NutrientType.SugarAlcohol to 7.4.grams,
            NutrientType.Calcium to 333.milligrams,
            NutrientType.Iron to 32.3.milligrams,
            NutrientType.Magnesium to 154.milligrams,
            NutrientType.Phosphorous to 123.milligrams,
            NutrientType.Potassium to 641.milligrams,
            NutrientType.Sodium to 487.milligrams,
            NutrientType.SaturatedFat to 1.28.grams,
            NutrientType.MonounsaturatedFat to 2.56.grams,
            NutrientType.PolyunsaturatedFat to 2.56.grams,
            NutrientType.TransFat to 0.grams,
            NutrientType.Cholesterol to 0.milligrams,
            NutrientType.Omega3 to 1.33.grams,
            NutrientType.Omega6 to 1.24.grams,
            NutrientType.Chloride to 13.grams,
            NutrientType.VitaminA to 10.milligrams,
            NutrientType.VitaminC to 90.milligrams,
        )
    )

    val filledNonZeroNutritionA get() = FoodNutrition(
        portion = Portion(100.grams),
        foodEnergy = 359.kilocalories,
        mapOf(
            NutrientType.Protein to 12.8.grams,
            NutrientType.TotalFat to 6.41.grams,
            NutrientType.TotalCarbohydrate to 74.4.grams,
            NutrientType.Fiber to 10.3.grams,
            NutrientType.Starch to 8.11.grams,
            NutrientType.Sugar to 5.13.grams,
            NutrientType.SugarAlcohol to 7.4.grams,
            NutrientType.Calcium to 333.milligrams,
            NutrientType.Iron to 32.3.milligrams,
            NutrientType.Magnesium to 154.milligrams,
            NutrientType.Phosphorous to 123.milligrams,
            NutrientType.Potassium to 641.milligrams,
            NutrientType.Sodium to 487.milligrams,
            NutrientType.SaturatedFat to 1.28.grams,
            NutrientType.MonounsaturatedFat to 2.56.grams,
            NutrientType.PolyunsaturatedFat to 2.56.grams,
            NutrientType.TransFat to 2.31.grams,
            NutrientType.Cholesterol to 3.21.milligrams,
            NutrientType.Omega3 to 1.33.grams,
            NutrientType.Omega6 to 1.24.grams,
            NutrientType.Chloride to 13.grams,
            NutrientType.VitaminA to 10.milligrams,
            NutrientType.VitaminC to 90.milligrams,
        )
    )

    /**
     * Each property is unique by counting
     */
    val filledNutritionB get() = FoodNutrition(
        portion = Portion(1.grams),
        foodEnergy = 2.kilocalories,
        NutrientType.entries.withIndex().associate { (index, type) -> type to (index + 2).grams }
    )
}

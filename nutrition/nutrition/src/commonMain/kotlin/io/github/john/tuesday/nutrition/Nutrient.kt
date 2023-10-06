package io.github.john.tuesday.nutrition

import org.calamarfederal.physical.measurement.*

enum class NutrientType {
    Protein,

    TotalCarbohydrate,
    Fiber,
    Sugar,
    SugarAlcohol,
    Starch,

    TotalFat,
    MonounsaturatedFat,
    PolyunsaturatedFat,
    Omega3,
    Omega6,
    SaturatedFat,
    TransFat,
    Cholesterol,

    Calcium,
    Chloride,
    Iron,
    Magnesium,
    Phosphorous,
    Potassium,
    Sodium,

    VitaminA,
    VitaminC,
    ;
}

sealed interface Nutrient {
    val nutrientType: NutrientType
    val mass: Mass
}

internal data class NutrientImplementation(
    override val nutrientType: NutrientType,
    override val mass: Mass,
) : Nutrient

fun makeNutrient(nutrientType: NutrientType, mass: Mass): Nutrient = NutrientImplementation(nutrientType = nutrientType, mass = mass)
operator fun NutrientType.invoke(mass: Mass): Nutrient = makeNutrient(nutrientType = this, mass = mass)

val FoodNutrition.protein: Mass? get() = get(NutrientType.Protein)
val FoodNutrition.totalCarbohydrate: Mass? get() = get(NutrientType.TotalCarbohydrate)
val FoodNutrition.fiber: Mass? get() = get(NutrientType.Fiber)
val FoodNutrition.sugar: Mass? get() = get(NutrientType.Sugar)
val FoodNutrition.sugarAlcohol: Mass? get() = get(NutrientType.SugarAlcohol)
val FoodNutrition.starch: Mass? get() = get(NutrientType.Starch)
val FoodNutrition.totalFat: Mass? get() = get(NutrientType.TotalFat)
val FoodNutrition.monounsaturatedFat: Mass? get() = get(NutrientType.MonounsaturatedFat)
val FoodNutrition.polyunsaturatedFat: Mass? get() = get(NutrientType.PolyunsaturatedFat)
val FoodNutrition.omega3: Mass? get() = get(NutrientType.Omega3)
val FoodNutrition.omega6: Mass? get() = get(NutrientType.Omega6)
val FoodNutrition.saturatedFat: Mass? get() = get(NutrientType.SaturatedFat)
val FoodNutrition.transFat: Mass? get() = get(NutrientType.TransFat)
val FoodNutrition.cholesterol: Mass? get() = get(NutrientType.Cholesterol)
val FoodNutrition.calcium: Mass? get() = get(NutrientType.Calcium)
val FoodNutrition.chloride: Mass? get() = get(NutrientType.Chloride)

val FoodNutrition.iron: Mass? get() = get(NutrientType.Iron)
val FoodNutrition.magnesium: Mass? get() = get(NutrientType.Magnesium)
val FoodNutrition.phosphorous: Mass? get() = get(NutrientType.Phosphorous)
val FoodNutrition.potassium: Mass? get() = get(NutrientType.Potassium)
val FoodNutrition.sodium: Mass? get() = get(NutrientType.Sodium)
val FoodNutrition.vitaminA: Mass? get() = get(NutrientType.VitaminA)
val FoodNutrition.vitaminC: Mass? get() = get(NutrientType.VitaminC)

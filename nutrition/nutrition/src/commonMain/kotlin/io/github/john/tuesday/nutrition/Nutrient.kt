package io.github.john.tuesday.nutrition

import org.calamarfederal.physical.measurement.*

/**
 * Macro category of [NutrientType]
 */
enum class NutrientCategory {
    Protein,
    Carbohydrate,
    Fat,
    Mineral,
    Vitamin,
}

/**
 * Nutrient type with a specified [category]
 */
enum class NutrientType(val category: NutrientCategory) {
    Protein(NutrientCategory.Protein),

    TotalCarbohydrate(NutrientCategory.Carbohydrate),
    Fiber(NutrientCategory.Carbohydrate),
    Sugar(NutrientCategory.Carbohydrate),
    SugarAlcohol(NutrientCategory.Carbohydrate),
    Starch(NutrientCategory.Carbohydrate),

    TotalFat(NutrientCategory.Fat),
    MonounsaturatedFat(NutrientCategory.Fat),
    PolyunsaturatedFat(NutrientCategory.Fat),
    Omega3(NutrientCategory.Fat),
    Omega6(NutrientCategory.Fat),
    SaturatedFat(NutrientCategory.Fat),
    TransFat(NutrientCategory.Fat),
    Cholesterol(NutrientCategory.Fat),

    Calcium(NutrientCategory.Mineral),
    Chloride(NutrientCategory.Mineral),
    Iron(NutrientCategory.Mineral),
    Magnesium(NutrientCategory.Mineral),
    Phosphorous(NutrientCategory.Mineral),
    Potassium(NutrientCategory.Mineral),
    Sodium(NutrientCategory.Mineral),

    VitaminA(NutrientCategory.Vitamin),
    VitaminC(NutrientCategory.Vitamin),
    ;

    companion object
}

/**
 * All [NutrientType] with [NutrientType.category] equal to [NutrientCategory.Carbohydrate]
 */
fun NutrientType.Companion.allCarbohydrates(): List<NutrientType> =
    NutrientType.entries.filter { it.category == NutrientCategory.Carbohydrate }

/**
 * All [NutrientType] with [NutrientType.category] equal to [NutrientCategory.Fat]
 */
fun NutrientType.Companion.allFats(): List<NutrientType> =
    NutrientType.entries.filter { it.category == NutrientCategory.Fat }

/**
 * All [NutrientType] with [NutrientType.category] equal to [NutrientCategory.Mineral]
 */
fun NutrientType.Companion.allMinerals(): List<NutrientType> =
    NutrientType.entries.filter { it.category == NutrientCategory.Mineral }

/**
 * All [NutrientType] with [NutrientType.category] equal to [NutrientCategory.Vitamin]
 */
fun NutrientType.Companion.allVitamins(): List<NutrientType> =
    NutrientType.entries.filter { it.category == NutrientCategory.Vitamin }


/**
 * Specific amount of [NutrientType] in [Mass]
 */
sealed interface Nutrient {
    val nutrientType: NutrientType
    val mass: Mass

    companion object
}

internal data class NutrientImplementation(
    override val nutrientType: NutrientType,
    override val mass: Mass,
) : Nutrient

fun makeNutrient(nutrientType: NutrientType, mass: Mass): Nutrient =
    NutrientImplementation(nutrientType = nutrientType, mass = mass)

operator fun NutrientType.invoke(mass: Mass): Nutrient = makeNutrient(nutrientType = this, mass = mass)
operator fun Nutrient.Companion.invoke(nutrientType: NutrientType, mass: Mass): Nutrient =
    makeNutrient(nutrientType = nutrientType, mass = mass)


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

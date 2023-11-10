package io.github.john.tuesday.nutrition

import io.github.john.tuesday.measurement.Mass

/**
 * Macro category of [NutrientType]
 */
public enum class NutrientCategory {
    Protein,
    Carbohydrate,
    Fat,
    Mineral,
    Vitamin,
}

/**
 * Nutrient type with a specified [category]
 */
public enum class NutrientType(public val category: NutrientCategory) {
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

    public companion object
}

/**
 * All [NutrientType] with [NutrientType.category] equal to [NutrientCategory.Carbohydrate]
 */
public fun NutrientType.Companion.allCarbohydrates(): List<NutrientType> =
    NutrientType.entries.filter { it.category == NutrientCategory.Carbohydrate }

/**
 * All [NutrientType] with [NutrientType.category] equal to [NutrientCategory.Fat]
 */
public fun NutrientType.Companion.allFats(): List<NutrientType> =
    NutrientType.entries.filter { it.category == NutrientCategory.Fat }

/**
 * All [NutrientType] with [NutrientType.category] equal to [NutrientCategory.Mineral]
 */
public fun NutrientType.Companion.allMinerals(): List<NutrientType> =
    NutrientType.entries.filter { it.category == NutrientCategory.Mineral }

/**
 * All [NutrientType] with [NutrientType.category] equal to [NutrientCategory.Vitamin]
 */
public fun NutrientType.Companion.allVitamins(): List<NutrientType> =
    NutrientType.entries.filter { it.category == NutrientCategory.Vitamin }


/**
 * Specific amount of [NutrientType] in [Mass]
 */
public sealed interface Nutrient {
    public val nutrientType: NutrientType
    public val mass: Mass

    public companion object
}

internal data class NutrientImplementation(
    override val nutrientType: NutrientType,
    override val mass: Mass,
) : Nutrient

public fun makeNutrient(nutrientType: NutrientType, mass: Mass): Nutrient =
    NutrientImplementation(nutrientType = nutrientType, mass = mass)

public operator fun NutrientType.invoke(mass: Mass): Nutrient = makeNutrient(nutrientType = this, mass = mass)
public operator fun Nutrient.Companion.invoke(nutrientType: NutrientType, mass: Mass): Nutrient =
    makeNutrient(nutrientType = nutrientType, mass = mass)


public val FoodNutrition.protein: Mass? get() = get(NutrientType.Protein)
public val FoodNutrition.totalCarbohydrate: Mass? get() = get(NutrientType.TotalCarbohydrate)
public val FoodNutrition.fiber: Mass? get() = get(NutrientType.Fiber)
public val FoodNutrition.sugar: Mass? get() = get(NutrientType.Sugar)
public val FoodNutrition.sugarAlcohol: Mass? get() = get(NutrientType.SugarAlcohol)
public val FoodNutrition.starch: Mass? get() = get(NutrientType.Starch)
public val FoodNutrition.totalFat: Mass? get() = get(NutrientType.TotalFat)
public val FoodNutrition.monounsaturatedFat: Mass? get() = get(NutrientType.MonounsaturatedFat)
public val FoodNutrition.polyunsaturatedFat: Mass? get() = get(NutrientType.PolyunsaturatedFat)
public val FoodNutrition.omega3: Mass? get() = get(NutrientType.Omega3)
public val FoodNutrition.omega6: Mass? get() = get(NutrientType.Omega6)
public val FoodNutrition.saturatedFat: Mass? get() = get(NutrientType.SaturatedFat)
public val FoodNutrition.transFat: Mass? get() = get(NutrientType.TransFat)
public val FoodNutrition.cholesterol: Mass? get() = get(NutrientType.Cholesterol)
public val FoodNutrition.calcium: Mass? get() = get(NutrientType.Calcium)
public val FoodNutrition.chloride: Mass? get() = get(NutrientType.Chloride)

public val FoodNutrition.iron: Mass? get() = get(NutrientType.Iron)
public val FoodNutrition.magnesium: Mass? get() = get(NutrientType.Magnesium)
public val FoodNutrition.phosphorous: Mass? get() = get(NutrientType.Phosphorous)
public val FoodNutrition.potassium: Mass? get() = get(NutrientType.Potassium)
public val FoodNutrition.sodium: Mass? get() = get(NutrientType.Sodium)
public val FoodNutrition.vitaminA: Mass? get() = get(NutrientType.VitaminA)
public val FoodNutrition.vitaminC: Mass? get() = get(NutrientType.VitaminC)

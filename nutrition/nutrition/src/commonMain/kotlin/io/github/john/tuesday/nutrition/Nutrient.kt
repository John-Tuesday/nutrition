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
public sealed class Nutrient {
    public abstract val nutrientType: NutrientType
    public abstract val mass: Mass

    override fun equals(other: Any?): Boolean =
        other is Nutrient && nutrientType == other.nutrientType && mass == other.mass
    override fun toString(): String = "Nutrient(nutrientType=$nutrientType, mass=$mass)"
    override fun hashCode(): Int {
        var result = nutrientType.hashCode()
        result = 31 * result + mass.hashCode()
        return result
    }

    public companion object
}

/**
 * Basic implementation of [Nutrient]
 */
internal class NutrientImplementation(
    override val nutrientType: NutrientType,
    override val mass: Mass,
) : Nutrient()

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("Nutrient(NutrientType, Mass)"),
    level = DeprecationLevel.WARNING,
)
public fun makeNutrient(nutrientType: NutrientType, mass: Mass): Nutrient =
    NutrientImplementation(nutrientType = nutrientType, mass = mass)

/**
 * Create a [Nutrient] with matching [nutrientType] and [mass]
 */
public fun Nutrient(nutrientType: NutrientType, mass: Mass): Nutrient =
    NutrientImplementation(nutrientType = nutrientType, mass = mass)

/**
 * Create a [Nutrient] whose [Nutrient.nutrientType] is `this` and [Nutrient.mass] is [mass]
 */
public operator fun NutrientType.invoke(mass: Mass): Nutrient = Nutrient(nutrientType = this, mass = mass)

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.protein: Mass? get() = nutrients[NutrientType.Protein]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.totalCarbohydrate: Mass? get() = nutrients[NutrientType.TotalCarbohydrate]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.fiber: Mass? get() = nutrients[NutrientType.Fiber]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.sugar: Mass? get() = nutrients[NutrientType.Sugar]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.sugarAlcohol: Mass? get() = nutrients[NutrientType.SugarAlcohol]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.starch: Mass? get() = nutrients[NutrientType.Starch]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.totalFat: Mass? get() = nutrients[NutrientType.TotalFat]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.monounsaturatedFat: Mass? get() = nutrients[NutrientType.MonounsaturatedFat]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.polyunsaturatedFat: Mass? get() = nutrients[NutrientType.PolyunsaturatedFat]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.omega3: Mass? get() = nutrients[NutrientType.Omega3]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.omega6: Mass? get() = nutrients[NutrientType.Omega6]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.saturatedFat: Mass? get() = nutrients[NutrientType.SaturatedFat]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.transFat: Mass? get() = nutrients[NutrientType.TransFat]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.cholesterol: Mass? get() = nutrients[NutrientType.Cholesterol]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.calcium: Mass? get() = nutrients[NutrientType.Calcium]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.chloride: Mass? get() = nutrients[NutrientType.Chloride]


@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.iron: Mass? get() = nutrients[NutrientType.Iron]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.magnesium: Mass? get() = nutrients[NutrientType.Magnesium]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.phosphorous: Mass? get() = nutrients[NutrientType.Phosphorous]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.potassium: Mass? get() = nutrients[NutrientType.Potassium]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.sodium: Mass? get() = nutrients[NutrientType.Sodium]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.vitaminA: Mass? get() = nutrients[NutrientType.VitaminA]

@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.vitaminC: Mass? get() = nutrients[NutrientType.VitaminC]

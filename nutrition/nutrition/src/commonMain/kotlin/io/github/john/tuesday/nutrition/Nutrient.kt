package io.github.john.tuesday.nutrition

import io.github.john.tuesday.measurement.Mass

/**
 * Macro category of [NutrientType]
 */
public enum class NutrientCategory {

    /**
     * Macro nutrient group
     *
     * @see [NutrientCategory]
     */
    Protein,

    /**
     * Macro nutrient group
     *
     * @see [NutrientCategory]
     */
    Carbohydrate,

    /**
     * Macro nutrient group
     *
     * @see [NutrientCategory]
     */
    Fat,

    /**
     * Nutrient group
     *
     * @see [NutrientCategory]
     */
    Mineral,

    /**
     * Nutrient group
     *
     * @see [NutrientCategory]
     */
    Vitamin,
}

/**
 * Nutrient type with a specified [category]
 */
public enum class NutrientType(
    /**
     * The [NutrientCategory] that this belongs to
     */
    public val category: NutrientCategory
) {
    /**
     * Protein nutrient
     *
     * @see [NutrientType]
     */
    Protein(NutrientCategory.Protein),

    /**
     * Sum of all nutrients in category [NutrientCategory.Carbohydrate]
     *
     * @see [NutrientType]
     */
    TotalCarbohydrate(NutrientCategory.Carbohydrate),

    /**
     * Nutrient in category [NutrientCategory.Carbohydrate]
     *
     * @see [NutrientType]
     */
    Fiber(NutrientCategory.Carbohydrate),

    /**
     * Nutrient in category [NutrientCategory.Carbohydrate]
     *
     * @see [NutrientType]
     */
    Sugar(NutrientCategory.Carbohydrate),

    /**
     * Nutrient in category [NutrientCategory.Carbohydrate]
     *
     * @see [NutrientType]
     */
    SugarAlcohol(NutrientCategory.Carbohydrate),

    /**
     * Nutrient in category [NutrientCategory.Carbohydrate]
     *
     * @see [NutrientType]
     */
    Starch(NutrientCategory.Carbohydrate),

    /**
     * Sum of all nutrients in category [NutrientCategory.Carbohydrate]
     *
     * @see [NutrientType]
     */
    TotalFat(NutrientCategory.Fat),

    /**
     * Nutrient in category [NutrientCategory.Fat]
     *
     * @see [NutrientType]
     */
    MonounsaturatedFat(NutrientCategory.Fat),

    /**
     * Nutrient in category [NutrientCategory.Fat]
     *
     * @see [NutrientType]
     */
    PolyunsaturatedFat(NutrientCategory.Fat),

    /**
     * Nutrient in category [NutrientCategory.Fat]
     *
     * @see [NutrientType]
     */
    Omega3(NutrientCategory.Fat),

    /**
     * Nutrient in category [NutrientCategory.Fat]
     *
     * @see [NutrientType]
     */
    Omega6(NutrientCategory.Fat),

    /**
     * Nutrient in category [NutrientCategory.Fat]
     *
     * @see [NutrientType]
     */
    SaturatedFat(NutrientCategory.Fat),

    /**
     * Nutrient in category [NutrientCategory.Fat]
     *
     * @see [NutrientType]
     */
    TransFat(NutrientCategory.Fat),

    /**
     * Nutrient in category [NutrientCategory.Fat]
     *
     * @see [NutrientType]
     */
    Cholesterol(NutrientCategory.Fat),

    /**
     * Nutrient in category [NutrientCategory.Mineral]
     *
     * @see [NutrientType]
     */
    Calcium(NutrientCategory.Mineral),

    /**
     * Nutrient in category [NutrientCategory.Mineral]
     *
     * @see [NutrientType]
     */
    Chloride(NutrientCategory.Mineral),

    /**
     * Nutrient in category [NutrientCategory.Mineral]
     *
     * @see [NutrientType]
     */
    Iron(NutrientCategory.Mineral),

    /**
     * Nutrient in category [NutrientCategory.Mineral]
     *
     * @see [NutrientType]
     */
    Magnesium(NutrientCategory.Mineral),

    /**
     * Nutrient in category [NutrientCategory.Mineral]
     *
     * @see [NutrientType]
     */
    Phosphorous(NutrientCategory.Mineral),

    /**
     * Nutrient in category [NutrientCategory.Mineral]
     *
     * @see [NutrientType]
     */
    Potassium(NutrientCategory.Mineral),

    /**
     * Nutrient in category [NutrientCategory.Mineral]
     *
     * @see [NutrientType]
     */
    Sodium(NutrientCategory.Mineral),

    /**
     * Nutrient in category [NutrientCategory.Vitamin]
     *
     * @see [NutrientType]
     */
    VitaminA(NutrientCategory.Vitamin),

    /**
     * Nutrient in category [NutrientCategory.Vitamin]
     *
     * @see [NutrientType]
     */
    VitaminC(NutrientCategory.Vitamin),
    ;

    /**
     * Companion object for extensibility
     */
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
    /**
     * The nutrient being represented.
     *
     * This value is Immutable.
     */
    public abstract val nutrientType: NutrientType

    /**
     * The mass of the nutrient represented
     *
     * This value is Immutable
     */
    public abstract val mass: Mass

    /**
     * Returns true if and only if [other] is of type [Nutrient] with equal values for [nutrientType] and [mass].
     */
    override fun equals(other: Any?): Boolean =
        other is Nutrient && nutrientType == other.nutrientType && mass == other.mass

    /**
     * Returns a string representation of this object
     */
    override fun toString(): String = "Nutrient(nutrientType=$nutrientType, mass=$mass)"

    /**
     * Returns the hash code of this object
     */
    override fun hashCode(): Int {
        var result = nutrientType.hashCode()
        result = 31 * result + mass.hashCode()
        return result
    }

    /**
     * Companion object for extensibility
     */
    public companion object
}

/**
 * Basic implementation of [Nutrient]
 */
internal class NutrientImplementation(
    override val nutrientType: NutrientType,
    override val mass: Mass,
) : Nutrient()

/**
 * Construct a [Nutrient] with [nutrientType] and [mass]
 */
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

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.protein: Mass? get() = nutrients[NutrientType.Protein]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.totalCarbohydrate: Mass? get() = nutrients[NutrientType.TotalCarbohydrate]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.fiber: Mass? get() = nutrients[NutrientType.Fiber]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.sugar: Mass? get() = nutrients[NutrientType.Sugar]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.sugarAlcohol: Mass? get() = nutrients[NutrientType.SugarAlcohol]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.starch: Mass? get() = nutrients[NutrientType.Starch]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.totalFat: Mass? get() = nutrients[NutrientType.TotalFat]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.monounsaturatedFat: Mass? get() = nutrients[NutrientType.MonounsaturatedFat]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.polyunsaturatedFat: Mass? get() = nutrients[NutrientType.PolyunsaturatedFat]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.omega3: Mass? get() = nutrients[NutrientType.Omega3]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.omega6: Mass? get() = nutrients[NutrientType.Omega6]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.saturatedFat: Mass? get() = nutrients[NutrientType.SaturatedFat]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.transFat: Mass? get() = nutrients[NutrientType.TransFat]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.cholesterol: Mass? get() = nutrients[NutrientType.Cholesterol]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.calcium: Mass? get() = nutrients[NutrientType.Calcium]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.chloride: Mass? get() = nutrients[NutrientType.Chloride]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.iron: Mass? get() = nutrients[NutrientType.Iron]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.magnesium: Mass? get() = nutrients[NutrientType.Magnesium]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.phosphorous: Mass? get() = nutrients[NutrientType.Phosphorous]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.potassium: Mass? get() = nutrients[NutrientType.Potassium]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.sodium: Mass? get() = nutrients[NutrientType.Sodium]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.vitaminA: Mass? get() = nutrients[NutrientType.VitaminA]

/**
 * Convenience function for retrieving the [Mass] (or null if not present) of the nutrient with the same name
 */
@Deprecated(
    message = "Will be removed in 0.2.0",
    replaceWith = ReplaceWith("FoodNutrition.nutrients.get()"),
    level = DeprecationLevel.WARNING,
)
public val FoodNutrition.vitaminC: Mass? get() = nutrients[NutrientType.VitaminC]

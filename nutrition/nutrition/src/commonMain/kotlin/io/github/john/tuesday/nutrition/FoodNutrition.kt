package io.github.john.tuesday.nutrition

import io.github.john.tuesday.measurement.*

public typealias NutritionMap = Map<NutrientType, Mass>

internal val Map.Entry<NutrientType, Mass>.nutrientType: NutrientType get() = key
internal val Map.Entry<NutrientType, Mass>.mass: Mass get() = value

/**
 * Nutritional facts about something
 */
public sealed class FoodNutrition {
    public abstract val portion: Portion
    public abstract val foodEnergy: Energy
    public abstract val nutrients: NutritionMap
    public operator fun get(nutrientType: NutrientType): Mass? = nutrients[nutrientType]

    override fun toString(): String = "FoodNutrition(portion=$portion, foodEnergy=$foodEnergy, nutrients=$nutrients)"
    override fun equals(other: Any?): Boolean {
        if (other !is FoodNutrition || portion != other.portion || foodEnergy != other.foodEnergy)
            return false

        val otherEntries = other.nutrients.entries
        val entries = nutrients.entries
        return ((entries - otherEntries) + (otherEntries - entries)).isEmpty()
    }

    override fun hashCode(): Int {
        var result = portion.hashCode()
        result = 31 * result + foodEnergy.hashCode()
        result = 31 * result + nutrients.hashCode()
        return result
    }

    public companion object
}

public fun FoodNutrition(
    portion: Portion,
    foodEnergy: Energy = 0.kilocalories,
    nutrients: NutritionMap = mapOf()
): FoodNutrition = FoodNutritionMapImpl(portion = portion, foodEnergy = foodEnergy, nutrients = nutrients)

internal class FoodNutritionMapImpl(
    override val portion: Portion,
    override val foodEnergy: Energy,
    override val nutrients: NutritionMap,
) : FoodNutrition()

/**
 * Mirrors copy() of a `data class`
 */
public fun FoodNutrition.mutate(
    portion: Portion = this.portion,
    foodEnergy: Energy = this.foodEnergy,
    nutritionMap: NutritionMap = this.nutrients,
): FoodNutrition = FoodNutrition(portion = portion, foodEnergy = foodEnergy, nutrients = nutritionMap)

/**
 * Create a [FoodNutrition] with all properties scaled to make `portion == newPortion`
 *
 * @throws [MismatchPortionError] when `this.portion` and [newPortion] are not compatible types
 */
public fun FoodNutrition.scaleToPortion(newPortion: Portion): FoodNutrition {
    val scale = newPortion / portion
    return FoodNutrition(
        portion = portion * scale,
        foodEnergy = foodEnergy * scale,
        nutrients = nutrients.mapValues { it.mass * scale }
    )
}


/**
 * New [FoodNutrition] with each property increased by the respective property in [other]
 */
public operator fun FoodNutrition.plus(other: FoodNutrition): FoodNutrition {
    val map = nutrients.toMutableMap()
    for ((nutrientType, mass) in other.nutrients)
        map[nutrientType] = (map[nutrientType] ?: 0.grams) + mass

    return FoodNutrition(
        portion = portion + other.portion,
        foodEnergy = foodEnergy + other.foodEnergy,
        nutrients = map.toMap(),
    )
}

/**
 * New [FoodNutrition] with each property reduced by the respective property in [other]
 */
public operator fun FoodNutrition.minus(other: FoodNutrition): FoodNutrition {
    val map = nutrients.toMutableMap()
    for ((nutrientType, mass) in other.nutrients)
        map[nutrientType] = (map[nutrientType] ?: 0.grams) - mass

    return FoodNutrition(
        portion = portion - other.portion,
        foodEnergy = foodEnergy - other.foodEnergy,
        nutrients = map.toMap(),
    )
}

/**
 * Negate [FoodNutrition.portion], [FoodNutrition.foodEnergy], and all [FoodNutrition.nutrients]
 */
public operator fun FoodNutrition.unaryMinus(): FoodNutrition {
    return FoodNutrition(
        portion = -portion,
        foodEnergy = -foodEnergy,
        nutrients = nutrients.mapValues { -it.mass }
    )
}

/**
 * New [FoodNutrition] but with [nutrient] added to the existing value or inserted
 */
public operator fun FoodNutrition.plus(nutrient: Nutrient): FoodNutrition {
    return FoodNutrition(
        portion = portion,
        foodEnergy = foodEnergy,
        nutrients = nutrients.toMutableMap().apply {
            set(nutrient.nutrientType, nutrient.mass + (get(nutrient.nutrientType) ?: 0.grams))
        }.toMap()
    )
}

/**
 * New [FoodNutrition] but with [nutrient] subtracted from the existing value or insert the negative
 */
public operator fun FoodNutrition.minus(nutrient: Nutrient): FoodNutrition {
    return FoodNutrition(
        portion = portion,
        foodEnergy = foodEnergy,
        nutrients = nutrients.toMutableMap().apply {
            set(nutrient.nutrientType, (get(nutrient.nutrientType) ?: 0.grams) - nutrient.mass)
        }.toMap()
    )
}

package io.github.john.tuesday.nutrition

import org.calamarfederal.physical.measurement.*

public typealias NutritionMap = Map<NutrientType, Mass>
internal val Map.Entry<NutrientType, Mass>.nutrientType: NutrientType get() = key
internal val Map.Entry<NutrientType, Mass>.mass: Mass get() = value

/**
 * Nutritional facts about something
 */
public sealed interface FoodNutrition {
    public val portion: Portion
    public val foodEnergy: Energy
    public val nutrients: NutritionMap
    public operator fun get(nutrientType: NutrientType): Mass? = nutrients[nutrientType]

    public companion object {
        public operator fun invoke(
            portion: Portion,
            foodEnergy: Energy = 0.kilocalories,
            nutritionMap: NutritionMap = mapOf(),
        ): FoodNutrition = FoodNutritionMapImpl(portion = portion, foodEnergy = foodEnergy, nutrients = nutritionMap)
    }
}

internal data class FoodNutritionMapImpl(
    override val portion: Portion,
    override val foodEnergy: Energy,
    override val nutrients: NutritionMap,
) : FoodNutrition {}

/**
 * Mirrors copy() of a `data class`
 */
public fun FoodNutrition.mutate(
    portion: Portion = this.portion,
    foodEnergy: Energy = this.foodEnergy,
    nutritionMap: NutritionMap = this.nutrients,
): FoodNutrition = FoodNutrition(portion = portion, foodEnergy = foodEnergy, nutritionMap = nutritionMap)


/**
 * Create a [FoodNutrition] with all properties scaled to make `portion == newPortion`
 *
 * @throws [MismatchPortionError] when `this.portion` and [newPortion] are not compatible types
 */
public fun FoodNutrition.scaleToPortion(newPortion: Portion): FoodNutrition {
    val scale = newPortion / portion
    return FoodNutrition.invoke(
        portion = portion * scale,
        foodEnergy = foodEnergy * scale,
        nutritionMap = nutrients.mapValues { it.mass * scale }
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
        nutritionMap = map.toMap(),
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
        nutritionMap = map.toMap(),
    )
}

/**
 * Negate [FoodNutrition.portion], [FoodNutrition.foodEnergy], and all [FoodNutrition.nutrients]
 */
public operator fun FoodNutrition.unaryMinus(): FoodNutrition {
    return FoodNutrition.invoke(
        portion = -portion,
        foodEnergy = -foodEnergy,
        nutritionMap = nutrients.mapValues { -it.mass }
    )
}

/**
 * New [FoodNutrition] but with [nutrient] added to the existing value or inserted
 */
public operator fun FoodNutrition.plus(nutrient: Nutrient): FoodNutrition {
    return FoodNutrition(
        portion = portion,
        foodEnergy = foodEnergy,
        nutritionMap = nutrients.toMutableMap().apply {
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
        nutritionMap = nutrients.toMutableMap().apply {
            set(nutrient.nutrientType, (get(nutrient.nutrientType) ?: 0.grams) - nutrient.mass)
        }.toMap()
    )
}

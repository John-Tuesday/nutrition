package io.github.john.tuesday.nutrition

/**
 * Rudimentary formatting so that properties are on new lines
 */
public fun FoodNutrition.toPrettyString(): String = toString()
    .replaceFirst("(", "(\n")
    .replace("{", "{\n")
    .replace(",", ",\n")
    .replace("}", "\n}")
    .prependIndent("    ")
    .trimStart()

/**
 * Convert [foodNutrition] to a pretty string and print using [println]
 */
public fun prettyPrint(foodNutrition: FoodNutrition) {
    println(foodNutrition.toPrettyString())
}

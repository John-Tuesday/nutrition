package io.github.john.tuesday.nutrition

public fun FoodNutrition.toPrettyString(): String = toString()
    .replaceFirst("(", "(\n")
    .replace("{", "{\n")
    .replace(",", ",\n")
    .replace("}", "\n}")
    .prependIndent("    ")
    .trimStart()

public fun prettyPrint(foodNutrition: FoodNutrition) {
    println(foodNutrition.toPrettyString())
}

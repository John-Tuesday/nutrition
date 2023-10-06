package io.github.john.tuesday.nutrition

fun FoodNutrition.toPrettyString(): String = toString()
    .replaceFirst("(", "(\n")
    .replace("{", "{\n")
    .replace(",", ",\n")
    .replace("}", "\n}")
    .prependIndent("    ")
    .trimStart()

fun prettyPrint(foodNutrition: FoodNutrition) {
    println(foodNutrition.toPrettyString())
}

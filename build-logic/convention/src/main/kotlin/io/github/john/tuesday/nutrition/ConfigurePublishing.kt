package io.github.john.tuesday.nutrition

const val MavenGroupId = "io.github.john-tuesday"
const val MavenVersion = "0.2.0-alpha02"

data class RepositoryInfo(
    val name: String,
    val description: String,
    val repoUrl: String,
    val homeUrl: String = repoUrl,
    val documentationBaseUrl: String = "",
)

val NutritionRepo: RepositoryInfo = RepositoryInfo(
    name = "Nutrition",
    description = "Kotlin library which models human nutritional data",
    repoUrl = "https://github.com/John-Tuesday/nutrition",
    documentationBaseUrl = "https://john-tuesday.github.io/nutrition/documentation/nutrition/nutrition",
)

val NutritionTestRepo: RepositoryInfo = RepositoryInfo(
    name = "Nutrition Test",
    description = "Kotlin test helper library for Nutrition library",
    repoUrl = "https://github.com/John-Tuesday/nutrition",
    documentationBaseUrl = "https://john-tuesday.github.io/nutrition/documentation/nutrition/nutrition-test",
)


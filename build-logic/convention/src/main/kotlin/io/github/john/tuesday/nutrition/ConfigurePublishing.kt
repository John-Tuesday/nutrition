package io.github.john.tuesday.nutrition

import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.gradle.kotlin.dsl.assign


const val MavenGroupId = "io.github.john-tuesday"
const val MavenVersion = "0.2.0-alpha01"

public data class RepositoryInfo(
    val name: String,
    val description: String,
    val repoUrl: String,
    val homeUrl: String = repoUrl,
    val documentationBaseUrl: String = "",
)

public val NutritionRepo: RepositoryInfo = RepositoryInfo(
    name = "Nutrition",
    description = "Kotlin library which models human nutritional data",
    repoUrl = "https://github.com/John-Tuesday/nutrition",
    documentationBaseUrl = "https://john-tuesday.github.io/nutrition/documentation/nutrition/nutrition",
)

public val NutritionTestRepo: RepositoryInfo = RepositoryInfo(
    name = "Nutrition Test",
    description = "Kotlin test helper library for Nutrition library",
    repoUrl = "https://github.com/John-Tuesday/nutrition",
    documentationBaseUrl = "https://john-tuesday.github.io/nutrition/documentation/nutrition/nutrition-test",
)

internal fun MavenPomDeveloperSpec.johnTuesday() {
    developer {
        id = "John-Tuesday"
        name = "John Tuesday Picot"
        email = "calamarfederal.messyink@gmail.com"
    }
}


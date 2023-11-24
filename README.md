# nutrition

[![Release](https://img.shields.io/maven-central/v/io.github.john-tuesday/nutrition?logo=Sonatype&labelColor=white&logoColor=black&style=flat-square)](https://central.sonatype.com/search?q=nutrition&namespace=io.github.john-tuesday)
![multiplatform](https://img.shields.io/badge/kotlin-multiplatform-7F52FF?logo=Kotlin&labelColor=white&style=flat-square)
[![License](https://img.shields.io/github/license/John-Tuesday/nutrition?logo=Open-Source-Initiative&labelColor=white&style=flat-square)](LICENSE)

Kotlin library which models human nutritional data.

This library has an implicit dependency on [physical-measurement-units](https://github.com/John-Tuesday/physical-measurement-units).

## Getting started

First, add a dependency to this library.

<details>

<summary>Gradle (Kotlin DSL)</summary>

```kotlin
// build.gradle.kts

dependencies {
    implementation("io.github.john-tuesday:nutrition:$version")

    // Optional, adds test fixtures
    testImplementation("io.github.john-tuesday:nutrition-test:$version")
}
```

</details>

Now, use the library!

```kotlin
val mealNutrition = FoodNutrition(
    Portion(75.grams),
    310.kilocalories,
    mapOf(
        NutrientType.Protein to 12.8.grams,
        NutrientType.TotalFat to 6.41.grams,
        NutrientType.TotalCarbohydrate to 74.4.grams,
        NutrientType.Fiber to 10.3.grams,
        NutrientType.Sugar to 5.13.grams,
        NutrientType.Sodium to 487.milligrams,
        NutrientType.SaturatedFat to 1.28.grams,
        NutrientType.TransFat to 0.grams,
        // ...
        NutrientType.Calcium to 333.milligrams,
        NutrientType.Iron to 32.3.milligrams,
        NutrientType.Magnesium to 154.milligrams,
        NutrientType.Potassium to 641.milligrams,
    )
)
val extraSalt = Nutrient(NutrientType.Sodium, 750.milligrams)
val mealExtraSalt = mealNutrition + extraSalt
val mealNoSalt = mealNutrition - Nutrient(NutrientType.Sodium, mealNutrition.nutrients[NutrientType.Sodium])
```

## Documentation

Generated api documentation can be found [here](https://john-tuesday.github.io/nutrition/documentation/).

import io.github.john.tuesday.measurement.MavenGroupId
import io.github.john.tuesday.measurement.MavenVersion
import io.github.john.tuesday.measurement.NutritionTestRepo

plugins {
    id("nutrition.kotlin.library.multiplatform")
    id("nutrition.maven.publication")
}

group = MavenGroupId
version = MavenVersion

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom.name = NutritionTestRepo.name
        pom.description = NutritionTestRepo.description
        pom.url = NutritionTestRepo.homeUrl
    }
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":nutrition:nutrition"))
                implementation(libs.kotlin.test)
                implementation(libs.measure)
            }
        }
    }
}

import io.github.john.tuesday.nutrition.MavenGroupId
import io.github.john.tuesday.nutrition.MavenVersion
import io.github.john.tuesday.nutrition.NutritionRepo

plugins {
    id("nutrition.kotlin.library.multiplatform")
    id("nutrition.maven.publication")
    alias(libs.plugins.dokka.convention.html)
}

group = MavenGroupId
version = MavenVersion

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom.name = NutritionRepo.name
        pom.description = NutritionRepo.description
        pom.url = NutritionRepo.homeUrl
    }
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.measure)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(libs.junit.jupiter)
                runtimeOnly(libs.junit.platform.launcher)
            }
        }

    }
}

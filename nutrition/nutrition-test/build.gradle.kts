import io.github.john.tuesday.nutrition.MavenGroupId
import io.github.john.tuesday.nutrition.MavenVersion
import io.github.john.tuesday.nutrition.NutritionTestRepo
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import java.net.URL

plugins {
    id("nutrition.kotlin.library.multiplatform")
    id("nutrition.maven.publication")
    alias(libs.plugins.dokka)
}

group = MavenGroupId
version = MavenVersion

tasks.withType<DokkaTaskPartial>().configureEach {
    dokkaSourceSets.configureEach {
        includes.from("Module.md")
        reportUndocumented = true

        sourceLink {
            localDirectory = projectDir.resolve("src")
            remoteUrl = URL("${NutritionTestRepo.documentationBaseUrl}/src")
            remoteLineSuffix = "#L"
        }
    }
}

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

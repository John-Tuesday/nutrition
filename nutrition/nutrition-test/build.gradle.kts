import io.github.john.tuesday.measurement.MavenGroupId
import io.github.john.tuesday.measurement.MavenVersion

plugins {
    id("measure.kotlin.library")
    id("measure.kotlin.library.jvm")
//    id("measure.kotlin.library.android")
    id("measure.kotlin.library.native")
    id("measure.maven.publication")
}

group = MavenGroupId
version = MavenVersion

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

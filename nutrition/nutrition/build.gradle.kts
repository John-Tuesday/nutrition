import io.github.john.tuesday.measurement.MavenGroupId
import io.github.john.tuesday.measurement.MavenVersion

plugins {
    id("measure.kotlin.library")
    id("measure.kotlin.library.jvm")
    id("measure.kotlin.library.native")
    id("measure.maven.publication")
}

group = MavenGroupId
version = MavenVersion

dependencies {
    commonMainImplementation(libs.measure)
    commonTestImplementation(libs.kotlin.test)
    commonTestImplementation(libs.measure.test)
    jvmTestImplementation(libs.junit.jupiter)
    jvmTestRuntimeOnly(libs.junit.platform.launcher)
}

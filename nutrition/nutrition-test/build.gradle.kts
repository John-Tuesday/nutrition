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
    commonMainApi(project(":nutrition:nutrition"))
    commonMainImplementation(libs.kotlin.test)
}

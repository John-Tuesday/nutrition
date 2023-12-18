import io.github.john.tuesday.nutrition.MavenGroupId
import io.github.john.tuesday.nutrition.MavenVersion

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.maven.publish.assist) apply false
    id("nutrition.kotlin.library.multiplatform") apply false
    id("nutrition.maven.publication") apply false
    alias(libs.plugins.dokka.convention.html)
    alias(libs.plugins.dokka.convention.versioning)
}

group = MavenGroupId
version = MavenVersion

plugins {
    alias(libs.plugins.dokka) apply true
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.maven.publish.assist) apply false
}

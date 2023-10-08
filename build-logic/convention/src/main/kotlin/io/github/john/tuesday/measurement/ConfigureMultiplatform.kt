package io.github.john.tuesday.measurement

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Configure Kotlin Multiplatform common to all targets
 */
@OptIn(ExperimentalKotlinGradlePluginApi::class)
internal fun KotlinMultiplatformExtension.configureCommon() {
    targetHierarchy.default()
    configureKotlin()
}

/**
 * Configure Kotlin language settings for all targets
 */
internal fun KotlinMultiplatformExtension.configureKotlin() {
    sourceSets.configureEach {
        languageSettings {
            languageVersion = "1.9"
            apiVersion = "1.9"
            progressiveMode = true
        }
    }
}

/**
 * Configure the JVM target
 */
internal fun KotlinMultiplatformExtension.configureJvm() {
    jvm {
        jvmToolchain(8)
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
}

internal fun KotlinMultiplatformExtension.configureAndroid() {
    androidTarget {
        publishLibraryVariants = listOf("release", "debug")
    }
}
/**
 * Configure the native target
 */
internal fun KotlinMultiplatformExtension.configureNative() {
    linuxArm64()
    linuxX64()
    mingwX64()
//    macosX64()
//    macosArm64()
}

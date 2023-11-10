package io.github.john.tuesday.measurement

import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.assign
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

/**
 * Configure Kotlin language settings for all targets
 */
internal fun KotlinProjectExtension.configureCommon() {
    sourceSets.configureEach {
        languageSettings {
            explicitApi()
            languageVersion = Versions.kotlin.version
            apiVersion = Versions.kotlin.version
            progressiveMode = true
        }
    }
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(Versions.java.majorVersion)
    }
}

/**
 * Configure the JVM target
 */
internal fun KotlinMultiplatformExtension.configureJvm() {
    jvm {
        withJava()
        testRuns.named("test") {
            executionTask.configure {
                useJUnitPlatform()
            }
        }
    }
}

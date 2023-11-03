package io.github.john.tuesday.measurement

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

/**
 * Defines common versions shared across all conventions
 */
data object Versions {
    val kotlin: KotlinVersion = KotlinVersion.KOTLIN_1_9
    val java: JavaVersion = JavaVersion.VERSION_1_8
}

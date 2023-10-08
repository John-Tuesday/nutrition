import io.github.john.tuesday.measurement.configureAndroid
import io.github.john.tuesday.measurement.configureCommon
import io.github.john.tuesday.measurement.configureJvm
import io.github.john.tuesday.measurement.configureNative
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KotlinLibraryConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("org.jetbrains.kotlin.multiplatform")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configureCommon()
            }
        }
    }
}

class KotlinLibraryJvmConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("measure.kotlin.library")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configureJvm()
            }
        }
    }
}
class KotlinLibraryAndroidConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("measure.kotlin.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configureAndroid()
            }
        }
    }
}
class KotlinLibraryNativeConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("measure.kotlin.library")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configureNative()
            }
        }
    }
}

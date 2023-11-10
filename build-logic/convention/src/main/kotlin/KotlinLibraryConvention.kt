import io.github.john.tuesday.measurement.configureCommon
import io.github.john.tuesday.measurement.configureJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin

class KotlinLibraryConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.withType<KotlinBasePlugin>().configureEach {
                val kotlinExtension = extensions.getByType<KotlinProjectExtension>()
                kotlinExtension.configureCommon()
            }
        }
    }
}

class KotlinLibraryMultiplatformConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply<KotlinLibraryConvention>()
                apply("org.jetbrains.kotlin.multiplatform")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                applyDefaultHierarchyTemplate()
                configureJvm()
                linuxX64()
                linuxArm64()
                mingwX64()
            }
        }
    }
}

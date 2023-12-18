import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    `kotlin-dsl`
}

group = "io.github.john-tuesday.build-logic"

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        apiVersion = KotlinVersion.KOTLIN_1_9
        languageVersion = KotlinVersion.KOTLIN_1_9
        progressiveMode = true
    }
}

dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.kotlin.multiplatform.gradlePlugin)
    compileOnly(libs.maven.publish.assist.gradlePlugin)
}

gradlePlugin {
    plugins {
        val kotlinLibrary by registering {
            id = "nutrition.kotlin.library"
            implementationClass = "KotlinLibraryConvention"
        }
        val kotlinLibraryMultiplatform by registering {
            id = "nutrition.kotlin.library.multiplatform"
            implementationClass = "KotlinLibraryMultiplatformConvention"
        }

        val mavenPublication by registering {
            id = "nutrition.maven.publication"
            implementationClass = "MavenPublicationConvention"
        }
    }
}

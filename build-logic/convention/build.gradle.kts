import org.jetbrains.kotlin.gradle.dsl.JvmTarget
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
    compileOnly(libs.kotlin.android.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinLibrary") {
            id = "measure.kotlin.library"
            implementationClass = "KotlinLibraryConvention"
        }
        register("kotlinLibraryJvm") {
            id = "measure.kotlin.library.jvm"
            implementationClass = "KotlinLibraryJvmConvention"
        }
        register("kotlinLibraryAndroid") {
            id = "measure.kotlin.library.android"
            implementationClass = "KotlinLibraryAndroidConvention"
        }
        register("kotlinLibraryNative") {
            id = "measure.kotlin.library.native"
            implementationClass = "KotlinLibraryNativeConvention"
        }

        register("mavenPublication") {
            id = "measure.maven.publication"
            implementationClass = "MavenPublicationConvention"
        }
    }
}

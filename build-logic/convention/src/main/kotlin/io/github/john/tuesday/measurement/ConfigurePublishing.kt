package io.github.john.tuesday.measurement

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.gradle.api.publish.maven.MavenPomLicenseSpec
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.withType
import java.util.*


const val MavenGroupId = "io.github.john-tuesday"
const val MavenVersion = "0.1.1"

internal data class RepositoryInfo(
    val name: String,
    val description: String,
    val repoUrl: String,
    val homeUrl: String = repoUrl,
)

internal val NutritionRepo: RepositoryInfo = RepositoryInfo(
    name = "Nutrition",
    description = "Kotlin library which models human nutritional data",
    repoUrl = "https://github.com/John-Tuesday/nutrition",
)

internal fun Project.configureSecrets() {
    extra["signing.keyId"] = null
    extra["signing.password"] = null
    extra["signing.gnupg.keyName"] = null
    extra["signing.gnupg.passphrase"] = null
    extra["ossrhUsername"] = null
    extra["ossrhPassword"] = null

    val propsFile = this.rootProject.layout.projectDirectory.file("local.properties").asFile
    if (propsFile.exists()) {
        propsFile.reader().use {
            Properties().apply {
                load(it)
            }
        }.onEach { (name, value) ->
            extra[name.toString()] = value
        }
    }
}

internal fun PublishingExtension.configureRepositories(
    ossrhUsername: String?,
    ossrhPassword: String?,
) {
    repositories {
        maven {
            name = "sonatype"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
            metadataSources {
                gradleMetadata()
            }
        }
        maven {
            name = "sonatypeSnapshots"
            setUrl("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = ossrhUsername
                password = ossrhPassword
            }
            metadataSources {
                gradleMetadata()
            }
        }
    }
}

internal fun PublishingExtension.configureMaven(
    jarTask: TaskProvider<Jar>,
    repositoryInfo: RepositoryInfo = NutritionRepo,
) {
    publications.withType<MavenPublication>().configureEach {
        artifact(jarTask)
        withBuildIdentifier()

        pom {
            name.set(repositoryInfo.name)
            description.set(repositoryInfo.description)
            url.set(repositoryInfo.homeUrl)

            licenses {
                mit()
            }
            developers {
                johnTuesday()
            }
            scm {
                url.set(repositoryInfo.repoUrl)
            }
        }
    }
}

internal fun MavenPomLicenseSpec.mit() {
    license {
        name.set("MIT")
        url.set("https://opensource.org/licenses/MIT")
    }
}

internal fun MavenPomDeveloperSpec.johnTuesday() {
    developer {
        id.set("John-Tuesday")
        name.set("John Tuesday Picot")
        email.set("calamarfederal.messyink@gmail.com")
    }
}


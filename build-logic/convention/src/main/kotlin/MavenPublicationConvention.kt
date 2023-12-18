import io.github.john.tuesday.nutrition.NutritionRepo
import io.github.john.tuesday.plugins.MavenPublishAssistPlugin
import io.github.john.tuesday.plugins.helper.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType

class MavenPublicationConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(MavenPublishAssistPlugin::class)
            }

            val publishing = extensions.getByType<PublishingExtension>()
            publishing.repositories {
                maven {
                    usePreset(SonatypeStaging, providers)
                }
                maven {
                    usePreset(GitHubPackages(owner = "john-tuesday", repository = "nutrition"), providers)
                }
            }
            publishing.publications.withType<MavenPublication>().configureEach {
                pom {
                    scm {
                        url = NutritionRepo.repoUrl
                    }
                    licenses {
                        license(LicensePreset.MIT)
                    }
                    developers {
                        johnTuesday()
                    }
                }
            }
        }
    }
}

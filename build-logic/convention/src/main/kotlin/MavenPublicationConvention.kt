import io.github.john.tuesday.nutrition.NutritionRepo
import io.github.john.tuesday.nutrition.johnTuesday
import io.github.john.tuesday.plugins.MavenPublishAssistPlugin
import io.github.john.tuesday.plugins.publishing.model.LicensePreset
import io.github.john.tuesday.plugins.publishing.model.MavenRepository
import io.github.john.tuesday.plugins.publishing.model.license
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.credentials.PasswordCredentials
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.*

class MavenPublicationConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(MavenPublishAssistPlugin::class)
            }

            val publishing = extensions.getByType<PublishingExtension>()
            publishing.repositories {
                maven {
                    setUrl(MavenRepository.SonatypeStaging.url)
                    credentials(PasswordCredentials::class)
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

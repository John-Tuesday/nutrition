import io.github.john.tuesday.measurement.configureMaven
import io.github.john.tuesday.measurement.configureRepositories
import io.github.john.tuesday.measurement.configureSecrets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*
import org.gradle.plugins.signing.SigningExtension
import org.gradle.plugins.signing.SigningPlugin

class MavenPublicationConvention : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("maven-publish")
                apply<SigningPlugin>()
            }

            configureSecrets()

            val javadocJar by tasks.registering(Jar::class) {
                archiveClassifier.set("javadoc")
            }

            extensions.configure<PublishingExtension> {
                configureRepositories(
                    extra["ossrhUsername"].toString(),
                    extra["ossrhPassword"].toString(),
                )
                configureMaven(jarTask = javadocJar)
            }
            extensions.configure<SigningExtension> {
                useGpgCmd()
                sign(extensions.getByType<PublishingExtension>().publications)
            }
        }
    }
}

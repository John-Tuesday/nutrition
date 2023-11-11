import io.github.john.tuesday.nutrition.MavenGroupId
import io.github.john.tuesday.nutrition.MavenVersion

plugins {
    alias(libs.plugins.dokka) apply true
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.maven.publish.assist) apply false
    id("nutrition.kotlin.library.multiplatform") apply false
    id("nutrition.maven.publication") apply false
}

group = MavenGroupId
version = MavenVersion

val dokkaHtmlMultiModule = tasks.dokkaHtmlMultiModule

dokkaHtmlMultiModule {
    outputDirectory = rootProject.layout.projectDirectory.dir("docs/documentation")
    includes.from("Module.md")
}

tasks.withType<PublishToMavenRepository>().configureEach {
    dependsOn(dokkaHtmlMultiModule)
}

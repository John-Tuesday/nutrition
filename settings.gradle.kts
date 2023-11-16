pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/john-tuesday/*")//gradle-convention-plugins
            credentials {
                username = providers.gradleProperty("gpr.user").orElse(providers.environmentVariable("USERNAME")).get()
                password = providers.gradleProperty("gpr.key").orElse(providers.environmentVariable("TOKEN")).get()
            }
        }

    }
}
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "nutrition"
include(":nutrition:nutrition")
include(":nutrition:nutrition-test")

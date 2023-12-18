pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/tuesday-org/*")
            credentials {
                username = providers
                    .gradleProperty("gpr.user")
                    .orElse(providers.systemProperty("gpr.user"))
                    .orElse(providers.environmentVariable("GPR_USERNAME"))
                    .orNull ?: error("Expected property 'gpr.user' or environment variable 'GPR_USERNAME'")
                password = providers
                    .gradleProperty("gpr.key")
                    .orElse(providers.systemProperty("gpr.key"))
                    .orElse(providers.environmentVariable("GPR_TOKEN"))
                    .orNull ?: error("Expected property 'gpr.key' or environment variable 'GPR_TOKEN'")
            }
        }

    }
}
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/john-tuesday/*")
            credentials {
                username = providers.gradleProperty("gpr.user").orElse(providers.environmentVariable("USERNAME")).get()
                password = providers.gradleProperty("gpr.key").orElse(providers.environmentVariable("TOKEN")).get()
            }
        }
    }
}

rootProject.name = "nutrition"
include(":nutrition:nutrition")
include(":nutrition:nutrition-test")

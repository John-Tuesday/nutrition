dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/john-tuesday/*")
            credentials {
                username = providers.gradleProperty("gpr.user").orElse(providers.environmentVariable("USERNAME")).get()
                password = providers.gradleProperty("gpr.key").orElse(providers.environmentVariable("TOKEN")).get()
            }
        }
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")

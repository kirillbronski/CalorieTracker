pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "CalorieTracker"
include(":app")
include(":core:ui")
include(":core:domain")
include(":core:data")
include(":onboarding:domain")
include(":onboarding:presentation")
include(":tracker:data")
include(":tracker:domain")
include(":tracker:presentation")
include(":navigation")

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

rootProject.name = "PortailFamilles"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":feature:identity")
include(":feature:reservation")
include(":feature:mycity")
// core data modules
include(":core:presentation")
include(":core:kotlin")
include(":core:domain:usecase")
include(":core:domain:model")
include(":core:domain:irepository")
// core domain modules
include(":core:data:repository")
include(":core:data:idatasource")
include(":core:data:model")
include(":core:data:remote")
include(":core:data:local")
// embedded server
include(":server")

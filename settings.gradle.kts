pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

rootProject.name = "StudioGhibli"
rootProject.buildFileName = "build.gradle.kts"
include(":app")
include(":core-domain-movie")
include(":core-data-movie")
include(":core-model")
include(":core-data")
include(":core-domain")
include(":core-network-movie")
include(":core-database-movie")
include(":core-error")
include(":core-network")
include(":core-ui")
include(":listmovies:api")
include(":listmovies:impl")

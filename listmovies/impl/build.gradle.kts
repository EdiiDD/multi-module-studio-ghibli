@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    kotlin("kapt")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
    }

    packagingOptions {
        resources {
            excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":core-ui"))
    // Modules
    implementation(project(":listmovies:api"))
    implementation(project(":core-model"))
    implementation(project(":core-error"))
    implementation(project(":core-domain"))
    implementation(project(":core-domain-movie"))
    implementation(project(":core-network-movie"))

    //Composable
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewModelcompose)
    implementation(libs.androidx.startup)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.coil.compose)

    //DI
    implementation(libs.hilt.android)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.compiler)
    implementation(libs.hilt.dager)
    implementation(libs.hilt.android)
}
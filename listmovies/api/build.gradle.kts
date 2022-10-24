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

    //Composable
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.navigation.compose)

    //DI
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.dager)
}
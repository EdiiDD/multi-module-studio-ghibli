@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    api(project(":core-model"))
    implementation(project(":core-domain"))
    implementation(project(":core-error"))

    implementation(libs.androidx.corektx)

    //DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.lifecycle.runtime)

    // Kotlin
    implementation(libs.coroutines)
    implementation(libs.stdlib)
}


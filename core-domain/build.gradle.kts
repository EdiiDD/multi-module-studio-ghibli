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
    //Modules
    implementation(project(":core-model"))
    implementation(project(":core-error"))

    implementation(libs.androidx.corektx)

    //DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)

    //Kotlin
    implementation(libs.reflect)
    implementation(libs.stdlib)
    implementation(libs.coroutines)

    //Lifecycle
    implementation(libs.androidx.lifecycle.runtime)
}
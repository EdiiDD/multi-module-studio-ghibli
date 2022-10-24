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
    implementation(project(":core-network"))
    implementation(project(":core-domain"))

    //DI
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.dager)

    //Retrofit
    api(libs.okhttp.interceptor)
    api(libs.retrofit)
    api(libs.retrofit.moshi)
    api(libs.moshi)

    // Kotlin
    implementation(libs.coroutines)
    implementation(libs.stdlib)

    //Test
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mock.core)
    testImplementation(libs.mock.compiler)
    testImplementation(libs.okhttp.mockserver)
    testImplementation(libs.reflect)
}
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    kotlin("kapt")
}

android {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    namespace = "com.edy.studioghibli"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.edy.studioghibli"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "com.edy.studioghibli.AppTestRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    buildTypes {
        create("benchmark") {
            isDebuggable = true
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
        }
    }

    packagingOptions {
        resources {
            excludes.add("META-INF/gradle/incremental.annotation.processors")
        }
    }
    kapt {
        correctErrorTypes = true
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    //Modules
    implementation(project(":core-data-movie"))
    implementation(project(":core-ui"))
    implementation(project(":listmovies:api"))
    implementation(project(":listmovies:impl"))

    //Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)


    implementation(libs.hilt.android)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.dager)

    //DI
    kapt(libs.androidx.hilt.compiler)
    kapt(libs.hilt.compiler)
    kapt(libs.dager.compiler)
    kapt(libs.dager.processor)

    implementation(libs.androidx.startup)
}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.peterfam.dailyforecast"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.peterfam.dailyforecast"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", "\"${project.properties["API_KEY"]}\"")
        buildConfigField("String", "BASE_URL", "\"${project.properties["BASE_URL"]}\"")
        buildConfigField("String", "CITIES_URL", "\"${project.properties["CITIES_URL"]}\"")
        buildConfigField("String", "ICONS_BASE_URL", "\"${project.properties["ICONS_BASE_URL"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    kotlin {
        jvmToolchain(17)
    }
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    // Jetpack Compose
    implementation(libs.androidx.ui)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Retrofit and Moshi (for API calls and JSON parsing)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)

    //Room DB
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.material3.android)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.room.ktx)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.room.testing)

    // Room for Caching
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    //Hilt Android
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.coil)
    implementation(libs.coil.kt)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
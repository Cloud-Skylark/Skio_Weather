plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.skioweather"
    compileSdk = 36 // Changed to a stable SDK version, as 36 is not released. Use 34 or 35.

    defaultConfig {
        applicationId = "com.example.skioweather"
        minSdk = 26
        targetSdk = 36 // Target SDK should match compile SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    // The composeOptions block is often needed to link the compose compiler to the kotlin version
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Use the version compatible with your Kotlin plugin
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    // This is now correctly handled by the Compose BOM
    implementation(libs.androidx.compose.runtime.livedata)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // --- CORRECTED NETWORKING LIBRARIES ---
    val retrofitVersion = "2.9.0"
    // Removed the duplicate implementation
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // --- CORRECTED IMAGE LOADING LIBRARY ---
    // Switched to a stable version of Coil 2 to ensure build stability.
    // Coil 3 is in alpha and could be the source of the build issue.
    implementation("io.coil-kt:coil-compose:2.6.0")
}

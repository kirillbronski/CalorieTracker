plugins {
    alias(libs.plugins.conventions.androidLibrary)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.kbcoding.android.ui"
    buildFeatures {
        compose = true
    }
}

dependencies {

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
plugins {
    alias(libs.plugins.conventions.androidLibrary)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.kbcoding.android.tracker.presentation"

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.tracker.domain)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.coil)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
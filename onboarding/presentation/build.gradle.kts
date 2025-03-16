plugins {
    alias(libs.plugins.conventions.androidLibrary)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.kbcoding.android.onboarding.presentation"
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.onboarding.domain)
    implementation(projects.navigation)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
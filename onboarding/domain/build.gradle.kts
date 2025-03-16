plugins {
    alias(libs.plugins.conventions.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.kbcoding.android.onboarding.domain"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.ui)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
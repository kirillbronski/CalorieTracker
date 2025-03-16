plugins {
    alias(libs.plugins.conventions.androidLibrary)
    alias(libs.plugins.jetbrains.kotlin.serailization)
}

android {
    namespace = "com.kbcoding.android.navigation"
}

dependencies {

    implementation(projects.core.ui)
    implementation(projects.core.domain)

    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
plugins {
    alias(libs.plugins.conventions.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.kbcoding.android.tracker.domain"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.data)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    
    implementation(libs.coroutines.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
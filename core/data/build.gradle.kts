plugins {
    alias(libs.plugins.conventions.androidLibrary)
}

android {
    namespace = "com.kbcoding.android.data"
}

dependencies {

    implementation(projects.core.domain)
    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
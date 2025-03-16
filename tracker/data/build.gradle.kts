plugins {
    alias(libs.plugins.conventions.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.kbcoding.android.tracker.data"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.tracker.domain)

    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)

    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
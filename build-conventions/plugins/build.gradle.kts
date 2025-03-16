import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `kotlin-dsl`
}

val catalog = the<LibrariesForLibs>()

dependencies {
    implementation(files(catalog.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.kotlin.plugin)
    implementation(libs.android.plugin)
}

gradlePlugin {
    plugins {
        register("sharedAndroidLibraryConfig") {
            id = "com.kbcoding.conventions.android.library"
            implementationClass = "SharedAndroidLibraryConfig"
        }
    }
}

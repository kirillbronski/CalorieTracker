import com.android.build.gradle.LibraryExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.the
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class SharedAndroidLibraryConfig : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        val libs = target.the<LibrariesForLibs>()
        applyPlugins(libs)
        applyDependencies(libs)
        applyAndroidConfig()
        applyKotlinConfig()
    }

    private fun Project.applyAndroidConfig() = configure<LibraryExtension> {
        compileSdk = 35

        defaultConfig {
            minSdk = 27

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
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
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        buildFeatures {
            buildConfig = true
        }
    }

    private fun Project.applyKotlinConfig() = configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    private fun Project.applyDependencies(libs: LibrariesForLibs) = dependencies {
//        implementation(libs.hilt)
//        ksp(libs.hilt.compiler)

//        implementation(libs.androidx.core.ktx)
//        implementation(libs.androidx.appcompat)
        //implementation(libs.androidx.material3)
    }

    private fun Project.applyPlugins(libs: LibrariesForLibs) = with(pluginManager) {
        alias(libs.plugins.android.library)
        alias(libs.plugins.kotlin.android)
//        alias(libs.plugins.ksp)
//        alias(libs.plugins.hilt)
    }

    private fun PluginManager.alias(provider: Provider<PluginDependency>) {
        apply(provider.get().pluginId)
    }

    private fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
        add("implementation", dependencyNotation)
    }

    private fun DependencyHandlerScope.ksp(dependencyNotation: Any) {
        add("ksp", dependencyNotation)
    }

}
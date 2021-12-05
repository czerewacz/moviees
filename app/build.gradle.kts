import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.SAFEARGS)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.KOTLIN_PARCELIZE)

}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK
        targetSdk = BuildAndroidConfig.TARGET_SDK
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = DependenciesVersions.jvm
    }
}

dependencies {

    //Modules
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.Features.TVSHOWS))

    //Stdlib
    implementation(Dependencies.Kotlin.stdlib)

    //Android
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Google.material)
    implementation(Dependencies.AndroidX.constraintLayout)

    //Ktx
    implementation(Dependencies.AndroidX.ktx)

    //Android Lifecycle
    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.AndroidX.viewmodel)

    //Android Navigation
    implementation (Dependencies.AndroidX.navigation)
    implementation (Dependencies.AndroidX.navigationUI)

    //Dependency Injection
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.DI.koinAndroid)

    //Glide
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.glideKapt)

}
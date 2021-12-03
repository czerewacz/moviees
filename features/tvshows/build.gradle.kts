import extensions.addTestsDependencies

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK
        targetSdk = BuildAndroidConfig.TARGET_SDK

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //Modules
    implementation(project(BuildModules.CORE))

    //Stdlib
    implementation(Dependencies.Kotlin.stdlib)

    //Ktx
    implementation(Dependencies.AndroidX.ktx)

    //Dependency injection
    implementation(Dependencies.DI.koin)
    implementation(Dependencies.DI.koinAndroid)

    //Android
    implementation(Dependencies.AndroidX.viewmodel)
    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Google.material)

    addTestsDependencies()
}
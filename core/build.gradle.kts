import extensions.addTestsDependencies

plugins {
    id(BuildPlugins.ANDROID_LIBRARY)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.KOTLIN_SERIALIZATION) version "1.5.0"
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


    externalNativeBuild {
        cmake {
            path = file("CMakeLists.txt")
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

    //Stdlib
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.AndroidX.ktx)
    implementation(Dependencies.DI.koin)

    //Android
    implementation(Dependencies.AndroidX.appcompat)
    implementation(Dependencies.Google.material)

    //Network Libs
    implementation(Dependencies.Ktor.ktorCio)
    implementation(Dependencies.Ktor.ktorLog)
    implementation(Dependencies.Ktor.ktorSerial)
    implementation(Dependencies.Logging.slf4j)


    addTestsDependencies()
}
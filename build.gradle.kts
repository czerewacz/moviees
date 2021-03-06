// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (Dependencies.BuildPlugins.gradle)
        classpath (Dependencies.BuildPlugins.kotlin)
        classpath(Dependencies.BuildPlugins.navigation)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
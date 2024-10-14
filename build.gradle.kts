buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)
    }
} // Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(Linter.ktlint) version Linter.ktlintVersion
    id("com.android.library") version "8.7.1" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("org.jetbrains.kotlin.jvm") version "2.0.21" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

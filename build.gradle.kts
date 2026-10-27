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
    id("com.android.library") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.25" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.25" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

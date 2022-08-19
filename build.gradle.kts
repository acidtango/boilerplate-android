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
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

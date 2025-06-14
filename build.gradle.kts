plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias (libs.plugins.ksp) apply false
    alias (libs.plugins.androidx.room.gradle.plugin) apply false
    alias (libs.plugins.build.konfig) apply false
    kotlin("plugin.serialization") version libs.versions.kotlin.get() apply false
}
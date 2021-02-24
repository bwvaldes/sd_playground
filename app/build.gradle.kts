plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jlleitschuh.gradle.ktlint") version PluginVersions.ktlint
    id("org.jmailen.kotlinter") version PluginVersions.kotlinter
    id("com.diffplug.spotless") version PluginVersions.spotless
    kotlin("kapt")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildTools)

    defaultConfig {
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        applicationId = AppConfig.applicationId
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    AndroidXLibs.implementations.forEach { dependency -> implementation(dependency) }
    KotlinLibs.implementations.forEach { dependency -> implementation(dependency) }
    ThirdPartyLibs.implementations.forEach { dependency -> implementation(dependency) }
    TestLibs.run {
        testImplementations.forEach { dependency -> implementation(dependency) }
        androidTestImplementations.forEach { dependency -> implementation(dependency) }
    }
}

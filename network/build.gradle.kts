plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

android {

    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile(
                "proguard-android.txt"),
                "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.kotlin)

    testImplementation(Dependencies.junit)
    testRuntimeOnly(Dependencies.junitRuntime)
    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.mockito)
    androidTestImplementation(Dependencies.androidXJunit)

    implementation(Dependencies.androidXFragment)

    // NETWORK
    implementation(Dependencies.retrofit)
    implementation(Dependencies.converterMoshi)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.moshi)
    implementation(Dependencies.moshiKotlin)
    implementation(Dependencies.moshiAdapters)
    kapt(Dependencies.moshiCodeGen)

    // DI
    implementation(Dependencies.koin)
    implementation(Dependencies.koinAndroidScope)
}
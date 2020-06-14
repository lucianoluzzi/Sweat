plugins {
    id("com.android.library")
    id("kotlin-android")
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
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.kotlin)
    implementation(Dependencies.androidKtxCore)
    implementation(Dependencies.androidAppCompat)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidXJunit)
    androidTestImplementation(Dependencies.espressoCore)
}
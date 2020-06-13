plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    buildFeatures {
        dataBinding = true
    }

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
    implementation(project(":domain"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.kotlin)

    implementation(Dependencies.androidKtxCore)
    implementation(Dependencies.androidAppCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.androidXFragment)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.liveData)

    implementation(Dependencies.koin)
    implementation(Dependencies.koinViewModel)

    implementation(Dependencies.junit)

    androidTestImplementation(Dependencies.androidXJunit)
    androidTestImplementation(Dependencies.espressoCore)
}
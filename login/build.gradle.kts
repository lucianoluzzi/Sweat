plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

android {
    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":design"))
    implementation(project(":network"))
    implementation(project(":domain"))
    implementation(project(":tests"))
    testImplementation(project(":tests"))

    implementation(Dependencies.kotlin)

    implementation(Dependencies.koin)
    implementation(Dependencies.koinViewModel)

    implementation(Dependencies.androidKtxCore)
    implementation(Dependencies.androidAppCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.androidXFragment)
    implementation(Dependencies.constraintLayout)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshi)

    implementation(Dependencies.facebookLogin)
    implementation(Dependencies.googleLogin)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.coroutinesTest)
    androidTestImplementation(Dependencies.androidXJunit)
    androidTestImplementation(Dependencies.espressoCore)
}
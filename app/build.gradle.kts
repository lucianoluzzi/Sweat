plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("com.google.gms.google-services")
    //id("com.google.firebase.crashlytics")
}

android {
    signingConfigs {
        create("signin") {
            storeFile = file("/Users/lucianoluzzi/Documents/dev/Sweat/keystore/sweat-ks")
            keyAlias = "sweat-ks"
            storePassword = "alonem0b1lt3c"
            keyPassword = "alonem0b1lt3c"
        }
    }
    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        applicationId = "com.lucianoluzzi.fitnet"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 2
        versionName = "0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.md")
        exclude("META-INF/LICENSE-notice.md")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude( "META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(project(":network"))
    implementation(project(":login"))
    implementation(project(":design"))
    implementation(project(":analytics"))
    implementation(project(":workout"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.kotlin)

    implementation(Dependencies.androidKtxCore)
    implementation(Dependencies.androidAppCompat)
    implementation(Dependencies.constraintLayout)
    //implementation(Dependencies.crashlytics)
    implementation(Dependencies.firebaseAnalytics)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidXJunit)

    androidTestImplementation(Dependencies.espressoCore)

    implementation(Dependencies.koin)
    implementation(Dependencies.koinAndroidScope)
    implementation(Dependencies.koinViewModel)

    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.mockitoKotlin)
}
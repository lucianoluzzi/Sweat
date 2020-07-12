object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradle}"

    const val androidKtxCore = "androidx.core:core-ktx:${Versions.androidCoreKtx}"
    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val androidXAnnotation = "androidx.annotation:annotation:${Versions.androidXAnnotation}"
    const val androidXFragment = "androidx.fragment:fragment-ktx:${Versions.androidXFragment}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val safeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
    const val firebaseAnalytics =
        "com.google.firebase:firebase-analytics-ktx:${Versions.firebaseAnalytics}"
    const val crashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytics}"

    const val koin = "org.koin:koin-android:${Versions.koinVersion}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
    const val koinAndroidScope = "org.koin:koin-android-scope:${Versions.koinVersion}"

    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val junitRuntime = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val androidXJunit = "androidx.test.ext:junit:${Versions.androidXJUnit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val truth = "com.google.truth:truth:${Versions.googleTruth}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.converterMoshi}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLogin}"
    const val googleLogin = "com.google.android.gms:play-services-auth:${Versions.googleLogin}"

    private object Versions {
        const val kotlinVersion = "1.3.72"
        const val buildGradle = "4.0.0"

        const val androidCoreKtx = "1.3.0"
        const val appCompat = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val material = "1.1.0"
        const val androidXAnnotation = "1.1.0"
        const val androidXFragment = "1.2.4"
        const val liveData = "2.2.0"
        const val navigation = "2.3.0-rc01"
        const val fragment = "1.2.0"
        const val googleServices = "4.3.3"
        const val firebaseAnalytics = "17.4.4"
        const val crashlytics = "2.2.0"

        const val koinVersion = "2.1.5"
        const val mockitoKotlin = "2.2.0"
        const val mockitoCore = "3.3.3"

        const val coroutinesTest = "1.3.7"
        const val retrofit = "2.9.0"
        const val converterMoshi = "2.9.0"
        const val okhttp = "4.7.2"
        const val moshi = "1.9.2"

        const val junit = "5.7.0-M1"
        const val androidXJUnit = "1.1.1"
        const val espressoCore = "3.2.0"
        const val googleTruth = "1.0.1"

        const val facebookLogin = "[5,6)"
        const val googleLogin = "18.0.0"
    }
}


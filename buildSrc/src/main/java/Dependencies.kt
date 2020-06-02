object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"

    val androidKtxCore = "androidx.core:core-ktx:${Versions.androidCoreKtxVersion}"
    val androidAppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val koin = "org.koin:koin-android:${Versions.koinVersion}"
    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
    val koinAndroidScope = "org.koin:koin-android-scope:${Versions.koinVersion}"

    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    val mockito = "org.mockito:mockito-core:${Versions.mockitoCoreVersion}"
    val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junitVersion}"
    val junitRuntime = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitVersion}"
    val androidXJunit = "androidx.test.ext:junit:${Versions.androidXJUnit}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    val truth = "com.google.truth:truth:${Versions.googleTruth}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.converterMoshiVersion}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"

    private object Versions {
        const val androidCoreKtxVersion = "1.3.0"
        const val appCompat = "1.1.0"
        const val constraintLayout = "1.1.3"

        const val kotlinVersion = "1.3.72"

        const val koinVersion = "2.1.5"
        const val mockitoKotlinVersion = "2.2.0"
        const val mockitoCoreVersion = "3.3.3"

        const val retrofitVersion = "2.9.0"
        const val converterMoshiVersion = "2.9.0"
        const val okhttpVersion = "4.7.2"
        const val moshiVersion = "1.9.2"

        const val junitVersion = "5.3.1"
        const val androidXJUnit = "1.1.1"
        const val espressoCoreVersion = "3.2.0"
        const val googleTruth = "1.0.1"
    }
}


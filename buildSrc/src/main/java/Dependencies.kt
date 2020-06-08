object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"

    const val androidKtxCore = "androidx.core:core-ktx:${Versions.androidCoreKtxVersion}"
    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val androidXAnnotation = "androidx.annotation:annotation:${Versions.androidXAnnotationVersion}"
    const val androidXFragment = "androidx.fragment:fragment-ktx:${Versions.androidXFragment}"
    const val androidXConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidXConstraintLayout}"

    const val koin = "org.koin:koin-android:${Versions.koinVersion}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
    const val koinAndroidScope = "org.koin:koin-android-scope:${Versions.koinVersion}"

    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockitoCoreVersion}"
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junitVersion}"
    const val junitRuntime = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitVersion}"
    const val androidXJunit = "androidx.test.ext:junit:${Versions.androidXJUnit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    const val truth = "com.google.truth:truth:${Versions.googleTruth}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTestVersion}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.converterMoshiVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshiVersion}"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"

    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLoginVersion}"

    private object Versions {
        const val androidCoreKtxVersion = "1.3.0"
        const val appCompat = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val material = "1.1.0"
        const val androidXAnnotationVersion = "1.1.0"
        const val androidXFragment = "1.2.4"
        const val androidXConstraintLayout = "2.0.0-beta6"

        const val kotlinVersion = "1.3.72"

        const val koinVersion = "2.1.5"
        const val mockitoKotlinVersion = "2.2.0"
        const val mockitoCoreVersion = "3.3.3"

        const val coroutinesTestVersion = "1.3.7"
        const val retrofitVersion = "2.9.0"
        const val converterMoshiVersion = "2.9.0"
        const val okhttpVersion = "4.7.2"
        const val moshiVersion = "1.9.2"

        const val junitVersion = "5.3.1"
        const val androidXJUnit = "1.1.1"
        const val espressoCoreVersion = "3.2.0"
        const val googleTruth = "1.0.1"

        const val facebookLoginVersion = "[5,6)"
    }
}


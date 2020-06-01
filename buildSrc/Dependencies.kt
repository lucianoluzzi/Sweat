object Dependencies {

    val koin = "org.koin:koin-android:${Versions.koinVesion}"
    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koinVersion}"
    val koinAndroidScope = "org.koin:koin-android-scope:${Versions.koinVersion}"

    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    val mockito = "org.mockito:mockito-core:${Versions.mockitoCoreVersion}"
    val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junitVersion}"
    val junitRuntime = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitVersion}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.converterMoshiVersion}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"

    private object Versions {
        const val koinVersion = "2.1.5"
        const val mockitoKotlinVersion = "2.2.0"
        const val mockitoCoreVersion = "3.3.3"
        const val retrofitVersion = "2.9.0"
        const val converterMoshiVersion = "2.9.0"
        const val okhttpVersion = "4.7.2"
        const val moshiVersion = "1.9.2"
        const val junitVersion = "5.3.1"
    }
}


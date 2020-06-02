package com.lucianoluzzi.networkbuilder

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkBuilder {

    fun build() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}
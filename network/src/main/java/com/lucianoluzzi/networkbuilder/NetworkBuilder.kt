package com.lucianoluzzi.networkbuilder

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkBuilder {

    fun build() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
        .build()

    private fun getMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private companion object {
        private const val BASE_URL = "https://overgrowth-backend.herokuapp.com/"
    }
}
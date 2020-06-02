package com.lucianoluzzi.networkbuilder

import retrofit2.Retrofit

class APIProvider(private val retrofit: Retrofit) {

    fun <T> createService(interfaceClass: Class<T>): T {
        return retrofit.create(interfaceClass)
    }
}
package com.lucianoluzzi.networkbuilder.di

import com.lucianoluzzi.networkbuilder.NetworkBuilder
import org.koin.dsl.module
import retrofit2.Retrofit

object NetworkBuilderModule {
    val module = module {
        single {
            NetworkBuilder().build() as Retrofit
        }
    }
}
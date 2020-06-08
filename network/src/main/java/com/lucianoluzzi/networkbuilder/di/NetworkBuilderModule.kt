package com.lucianoluzzi.networkbuilder.di

import com.lucianoluzzi.networkbuilder.APIProvider
import com.lucianoluzzi.networkbuilder.NetworkBuilder
import com.lucianoluzzi.networkbuilder.NetworkExecutor
import org.koin.dsl.module
import retrofit2.Retrofit

object NetworkBuilderModule {
    val module = module {
        factory {
            NetworkBuilder().build() as Retrofit
        }

        factory {
            APIProvider(get())
        }

        factory {
            NetworkExecutor()
        }
    }
}
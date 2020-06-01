package com.lucianoluzzi.sweat

import android.app.Application
import com.lucianoluzzi.networkbuilder.di.NetworkBuilderModule
import org.koin.core.context.startKoin

class SweatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            val modules = listOf(
                NetworkBuilderModule.module
            )

            modules(modules)
        }
    }
}
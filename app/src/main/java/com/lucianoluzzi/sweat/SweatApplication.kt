package com.lucianoluzzi.sweat

import android.app.Application
import com.lucianoluzzi.login.di.LoginModule
import com.lucianoluzzi.networkbuilder.di.NetworkBuilderModule
import com.lucianoluzzi.workout.di.WorkoutModule
import org.koin.core.context.startKoin

class SweatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            val modules = listOf(
                NetworkBuilderModule.module,
                LoginModule.module,
                WorkoutModule.module
            )

            modules(modules)
        }
    }
}
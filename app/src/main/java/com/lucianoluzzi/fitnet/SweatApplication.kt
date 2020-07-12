package com.lucianoluzzi.fitnet

import android.app.Application
import com.lucianoluzzi.login.LoginModule
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
                AppModule.module,
                NetworkBuilderModule.module,
                LoginModule.module,
                WorkoutModule.module
            )

            modules(modules)
        }
    }
}
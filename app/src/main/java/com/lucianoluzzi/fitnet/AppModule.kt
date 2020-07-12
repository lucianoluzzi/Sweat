package com.lucianoluzzi.fitnet

import com.lucianoluzzi.fitnet.analytics.AnalyticsTracker
import com.lucianoluzzi.fitnet.analytics.FirebaseAnalytics
import com.lucianoluzzi.fitnet.analytics.FirebaseProvider
import org.koin.dsl.module

object AppModule {
    val module = module {
        single<AnalyticsTracker> {
            FirebaseAnalytics(FirebaseProvider().getFirebaseAnalytics())
        }
    }
}
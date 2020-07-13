package com.lucianoluzzi.analytics

import org.koin.dsl.module

object AnalyticsModule {
    val module = module {
        single<AnalyticsTracker> {
            FirebaseAnalytics(FirebaseProvider().getFirebaseAnalytics())
        }
    }
}
package com.lucianoluzzi.fitnet.analytics

import android.app.Activity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalytics(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsTracker {
    override fun trackScreen(activity: Activity, screenName: String) {
        firebaseAnalytics.setCurrentScreen(activity, screenName, null)
    }

    override fun trackEvent(key: String, values: Map<String, String?>?) {
        val bundle: Bundle? = values?.let {
            val bundle = Bundle()
            for (entry: Map.Entry<String, String?> in it.entries) {
                bundle.putString(entry.key, entry.value)
            }
            bundle
        }
        firebaseAnalytics.logEvent(key, bundle)
    }
}
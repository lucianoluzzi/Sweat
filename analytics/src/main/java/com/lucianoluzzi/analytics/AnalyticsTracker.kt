package com.lucianoluzzi.analytics

import android.app.Activity

interface AnalyticsTracker {
    fun trackScreen(activity: Activity, screenName: String)
    fun trackEvent(key: String, values: Map<String, String?>? = null)
}
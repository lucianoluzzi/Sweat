package com.lucianoluzzi.workout.post.data

import android.app.Activity
import com.lucianoluzzi.analytics.AnalyticsTracker

class WorkoutTracker(private val analytics: AnalyticsTracker) {

    fun trackScreenShown(activity: Activity) = analytics.trackScreen(activity, SCREEN_NAME)

    fun trackBackButton() = analytics.trackEvent(BACK_BUTTON)

    fun trackCancelButton() = analytics.trackEvent(CANCEL_BUTTON)

    fun trackShareButton() = analytics.trackEvent(SHARE_BUTTON)

    fun trackDismissed() = analytics.trackEvent(DISMISSED)

    private companion object {
        const val SCREEN_NAME = "Share Workout"
        const val BACK_BUTTON = "BACK_BUTTON"
        const val CANCEL_BUTTON = "CANCEL_BUTTON"
        const val SHARE_BUTTON = "SHARE_BUTTON"
        const val DISMISSED = "DISMISSED"
    }
}
package com.lucianoluzzi.login.data.analytics

import android.app.Activity
import com.lucianoluzzi.analytics.AnalyticsTracker

class LoginTracker(private val analytics: AnalyticsTracker) {

    fun trackScreenShown(activity: Activity) = analytics.trackScreen(activity, SCREEN_NAME)

    fun trackBackButton() = analytics.trackEvent(BACK_BUTTON)

    fun trackFacebookButtonClicked() = analytics.trackEvent(FACEBOOK_BUTTON)

    fun trackGoogleSignInClicked() = analytics.trackEvent(GOOGLE_SIGN_IN)

    private companion object {
        const val SCREEN_NAME = "Login"

        const val BACK_BUTTON = "BACK_BUTTON"
        const val FACEBOOK_BUTTON = "FACEBOOK_BUTTON"
        const val GOOGLE_SIGN_IN = "GOOGLE_SIGN_IN"
    }
}
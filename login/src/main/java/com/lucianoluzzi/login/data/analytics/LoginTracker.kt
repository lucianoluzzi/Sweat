package com.lucianoluzzi.login.data.analytics

import android.app.Activity
import com.lucianoluzzi.analytics.AnalyticsTracker
import com.lucianoluzzi.domain.Profile

class LoginTracker(private val analytics: AnalyticsTracker) {

    fun trackScreenShown(activity: Activity) = analytics.trackScreen(activity, SCREEN_NAME)

    fun trackBackButton() = analytics.trackEvent(BACK_BUTTON)

    fun trackFacebookButtonClicked() = analytics.trackEvent(FACEBOOK_BUTTON)

    fun trackGoogleSignInClicked() = analytics.trackEvent(GOOGLE_SIGN_IN)

    fun trackUserLogin(profile: Profile) {
        analytics.trackEvent(USER_LOG_IN, mapOf(
            PROFILE_NAME to profile.name,
            PROFILE_EMAIL to profile.email,
            PROFILE_PICTURE to profile.imageUrl
        ))
    }

    private companion object {
        const val SCREEN_NAME = "Login"

        const val BACK_BUTTON = "BACK_BUTTON"
        const val FACEBOOK_BUTTON = "FACEBOOK_BUTTON"
        const val GOOGLE_SIGN_IN = "GOOGLE_SIGN_IN"
        const val USER_LOG_IN = "USER_LOG_IN"

        const val PROFILE_NAME = "PROFILE_NAME"
        const val PROFILE_EMAIL = "PROFILE_EMAIL"
        const val PROFILE_PICTURE = "PROFILE_PICTURE"
    }
}
package com.lucianoluzzi.login.domain.entities.facebook

import com.facebook.AccessToken
import com.facebook.Profile

class FacebookSessionManager {
    val profile: Profile?
        get() = Profile.getCurrentProfile()
    val accessToken: AccessToken?
        get() = AccessToken.getCurrentAccessToken()

    fun isSignedIn(): Boolean = accessToken != null && profile != null
}
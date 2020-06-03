package com.lucianoluzzi.login.domain.usecases

import com.facebook.AccessToken
import com.facebook.Profile

interface ConvertFacebookProfileUseCase {
    suspend fun convertFacebookProfile(profile: Profile, accessToken: AccessToken): com.lucianoluzzi.login.domain.entities.Profile
}
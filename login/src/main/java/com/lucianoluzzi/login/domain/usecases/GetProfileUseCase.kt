package com.lucianoluzzi.login.domain.usecases

import com.facebook.AccessToken
import com.lucianoluzzi.login.domain.entities.Profile

interface GetProfileUseCase {
    suspend fun getProfile(facebookProfile: com.facebook.Profile, accessToken: AccessToken): Profile
}
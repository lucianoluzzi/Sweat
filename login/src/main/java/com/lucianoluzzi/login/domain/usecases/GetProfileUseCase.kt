package com.lucianoluzzi.login.domain.usecases

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.lucianoluzzi.login.domain.entities.Profile

interface GetProfileUseCase {
    suspend fun getProfile(facebookProfile: com.facebook.Profile, accessToken: AccessToken): Profile
    suspend fun getProfile(googleSignInAccount: GoogleSignInAccount): Profile
}
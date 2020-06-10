package com.lucianoluzzi.login.domain.usecases

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.lucianoluzzi.domain.Profile

interface GetProfileUseCase {
    suspend fun getProfile(facebookProfile: com.facebook.Profile, accessToken: AccessToken): com.lucianoluzzi.domain.Profile
    suspend fun getProfile(googleSignInAccount: GoogleSignInAccount): com.lucianoluzzi.domain.Profile
}
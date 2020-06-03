package com.lucianoluzzi.login.repository.network

import com.facebook.AccessToken

interface FacebookRepository {
    suspend fun getEmail(accessToken: AccessToken): String?
}
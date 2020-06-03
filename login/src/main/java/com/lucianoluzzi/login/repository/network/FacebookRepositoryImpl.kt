package com.lucianoluzzi.login.repository.network

import com.facebook.AccessToken
import com.facebook.GraphRequest
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FacebookRepositoryImpl : FacebookRepository {
    override suspend fun getEmail(accessToken: AccessToken) = doRequest(accessToken)

    private suspend fun doRequest(accessToken: AccessToken): String? =
        suspendCoroutine { continuation ->
            GraphRequest.newMeRequest(accessToken) { jsonObject, _ ->
                val email = jsonObject.getString(EMAIL_JSON_KEY)
                continuation.resume(email)
            }
        }

    private companion object {
        const val EMAIL_JSON_KEY = "email"
    }
}
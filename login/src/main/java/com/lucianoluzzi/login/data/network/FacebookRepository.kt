package com.lucianoluzzi.login.data.network

import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.GraphRequest
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class FacebookRepository {
    suspend fun getEmail(accessToken: AccessToken) = doRequest(accessToken)

    private suspend fun doRequest(accessToken: AccessToken): String? =
        suspendCoroutine { continuation ->

            val bundle = Bundle()
            bundle.putString(FIELDS_REQUEST_PARAMETER_KEY, EMAIL_REQUEST_PARAMETER_KEY)

            val request = GraphRequest.newMeRequest(accessToken) { jsonObject, response ->
                continuation.resume(jsonObject.getString(EMAIL_JSON_KEY))
            }.apply {
                parameters = bundle
            }
            request.executeAndWait()
        }

    private companion object {
        const val EMAIL_JSON_KEY = "email"
        const val FIELDS_REQUEST_PARAMETER_KEY = "fields"
        const val EMAIL_REQUEST_PARAMETER_KEY = "email"
    }
}
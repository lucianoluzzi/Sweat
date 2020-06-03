package com.lucianoluzzi.login.ui.extensions

import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.lucianoluzzi.login.ui.uimodel.FacebookLoginResponseState
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun LoginButton.onLogin(callbackManager: CallbackManager): FacebookLoginResponseState =
    suspendCoroutine { continuation ->
        registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                continuation.resume(FacebookLoginResponseState.Success<LoginResult?>(result))
            }

            override fun onCancel() {
                continuation.resume(FacebookLoginResponseState.Cancel)
            }

            override fun onError(error: FacebookException?) {
                continuation.resume(FacebookLoginResponseState.Error<FacebookException?>(error))
            }
        })
}
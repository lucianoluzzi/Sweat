package com.lucianoluzzi.login.ui.uimodel

import com.facebook.FacebookException
import com.facebook.login.LoginResult

sealed class FacebookLoginResponseState {
    object Cancel : FacebookLoginResponseState()
    data class Success<T>(val response: LoginResult?) : FacebookLoginResponseState()
    data class Error<T>(val error: FacebookException?) : FacebookLoginResponseState()
}
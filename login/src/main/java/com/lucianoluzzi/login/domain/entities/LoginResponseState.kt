package com.lucianoluzzi.login.domain.entities

import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.networkbuilder.domain.entities.ErrorResponse

sealed class LoginResponseState {
    object Cancel : LoginResponseState()
    object Loading : LoginResponseState()
    data class Error(val error: ErrorResponse?) : LoginResponseState()
    data class Success(val response: Profile) : LoginResponseState()
}
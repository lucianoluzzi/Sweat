package com.lucianoluzzi.login.domain.entities

sealed class LoginResponseState {
    object Cancel : LoginResponseState()
    object Loading : LoginResponseState()
    data class Error<T>(val error: T?) : LoginResponseState()
    data class Success<T>(val response: T) : LoginResponseState()
}
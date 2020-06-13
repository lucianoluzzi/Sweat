package com.lucianoluzzi.login.repository.network.response

sealed class LoginResponse {
    data class Success<T>(val responseData: T): LoginResponse()
    data class Error<T>(val error: T?): LoginResponse()
}
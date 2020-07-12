package com.lucianoluzzi.login.data.network.response

sealed class LoginResponseWrapper {
    data class Success<T>(val responseData: T): LoginResponseWrapper()
    data class Error<T>(val error: T?): LoginResponseWrapper()
}
package com.lucianoluzzi.login.repository.network

import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.login.repository.network.response.LoginResponseWrapper

interface LoginRepository {
    suspend fun doLogin(loginRequest: LoginRequest): LoginResponseWrapper
}
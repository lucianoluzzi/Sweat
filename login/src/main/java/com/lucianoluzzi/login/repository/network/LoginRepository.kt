package com.lucianoluzzi.login.repository.network

import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.login.repository.network.response.LoginResponse

interface LoginRepository {
    suspend fun doLogin(loginRequest: LoginRequest): LoginResponse
}
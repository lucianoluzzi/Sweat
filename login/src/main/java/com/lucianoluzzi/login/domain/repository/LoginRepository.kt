package com.lucianoluzzi.login.domain.repository

import com.lucianoluzzi.login.data.network.request.LoginRequest
import com.lucianoluzzi.login.data.network.response.LoginResponseWrapper

interface LoginRepository {
    suspend fun doLogin(loginRequest: LoginRequest): LoginResponseWrapper
}
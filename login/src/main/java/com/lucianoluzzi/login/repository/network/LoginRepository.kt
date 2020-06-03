package com.lucianoluzzi.login.repository.network

import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.repository.network.request.LoginRequest

interface LoginRepository {
    suspend fun doLogin(loginRequest: LoginRequest): LoginResponseState
}
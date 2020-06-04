package com.lucianoluzzi.login.repository.network.service

import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.login.repository.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    suspend fun doLogin(@Body loginRequest: LoginRequest): LoginResponse
}
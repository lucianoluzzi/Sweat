package com.lucianoluzzi.login.repository.network

import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.networkbuilder.APIProvider

class LoginRepositoryImpl(private val apiProvider: APIProvider) :
    LoginRepository {

    override suspend fun doLogin(loginRequest: LoginRequest): LoginResponseState {
        val service = apiProvider.createService(LoginService::class.java)
        val loginResponse = service.doLogin(loginRequest)
        return LoginResponseState.Success(loginResponse)
    }
}
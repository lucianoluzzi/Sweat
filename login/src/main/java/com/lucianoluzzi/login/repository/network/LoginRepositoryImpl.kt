package com.lucianoluzzi.login.repository.network

import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.login.repository.network.response.LoginResponse
import com.lucianoluzzi.login.repository.network.service.LoginService
import com.lucianoluzzi.networkbuilder.APIProvider
import com.lucianoluzzi.networkbuilder.NetworkExecutor
import com.lucianoluzzi.networkbuilder.domain.entities.ResultWrapper
import com.lucianoluzzi.tests.DispatcherRegistry

class LoginRepositoryImpl(
    private val apiProvider: APIProvider,
    private val networkExecutor: NetworkExecutor
) :
    LoginRepository {

    override suspend fun doLogin(loginRequest: LoginRequest): LoginResponse {
        val service = apiProvider.createService(LoginService::class.java)
        val loginResponse = networkExecutor.safeApiCall(DispatcherRegistry.IO) {
            service.doLogin(loginRequest.profile)
        }

        return getLoginResponse(loginResponse)
    }

    private fun getLoginResponse(resultWrapper: ResultWrapper<*>): LoginResponse =
        when(resultWrapper) {
            is ResultWrapper.Success -> { LoginResponse.Success(resultWrapper.value) }
            is ResultWrapper.GenericError -> LoginResponse.Error(resultWrapper.error)
            is ResultWrapper.NetworkError -> LoginResponse.Error(null)
        }
}
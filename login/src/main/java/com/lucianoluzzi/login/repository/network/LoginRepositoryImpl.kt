package com.lucianoluzzi.login.repository.network

import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.login.repository.network.response.LoginResponseWrapper
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

    override suspend fun doLogin(loginRequest: LoginRequest): LoginResponseWrapper {
        val service = apiProvider.createService(LoginService::class.java)
        val loginResponse = networkExecutor.safeApiCall(DispatcherRegistry.IO) {
            service.doLogin(loginRequest.profile)
        }

        return getLoginResponse(loginResponse)
    }

    private fun getLoginResponse(resultWrapper: ResultWrapper<*>): LoginResponseWrapper =
        when(resultWrapper) {
            is ResultWrapper.Success -> { LoginResponseWrapper.Success(resultWrapper.value) }
            is ResultWrapper.GenericError -> LoginResponseWrapper.Error(resultWrapper.error)
            is ResultWrapper.NetworkError -> LoginResponseWrapper.Error(null)
        }
}
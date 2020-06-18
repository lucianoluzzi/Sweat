package com.lucianoluzzi.login.repository.network

import com.lucianoluzzi.login.CoroutineScopeExtension
import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.login.repository.network.service.LoginService
import com.lucianoluzzi.networkbuilder.APIProvider
import com.lucianoluzzi.networkbuilder.NetworkExecutor
import com.lucianoluzzi.networkbuilder.domain.entities.ResultWrapper
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineScopeExtension::class)
class LoginRepositoryImplTest {
    private val netWorkExecutor = mock<NetworkExecutor>()
    private val apiProvider = mock<APIProvider>()
    private val loginRepositoryImpl = LoginRepositoryImpl(apiProvider, netWorkExecutor)

    @Test
    fun `verify apiProvider called with LoginService when doLogin`() = runBlockingTest {
        val mockedService = mock<LoginService>()
        val loginRequest = getLoginRequest()

        whenever(apiProvider.createService(LoginService::class.java)).thenReturn(mockedService)
        whenever(netWorkExecutor.safeApiCall(any()) {
            mockedService.doLogin(loginRequest.profile)
        }).thenReturn(ResultWrapper.GenericError())

        loginRepositoryImpl.doLogin(loginRequest)

        // TODO: fix this test
        verify(apiProvider).createService(LoginService::class.java)
    }

    private fun getLoginRequest(): LoginRequest {
        return LoginRequest(
            Profile(
                email = "",
                name = ""
            )
        )
    }
}
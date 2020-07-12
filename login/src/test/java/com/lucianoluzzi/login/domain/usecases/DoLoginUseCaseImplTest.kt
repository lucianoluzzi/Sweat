package com.lucianoluzzi.login.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.login.CoroutineScopeExtension
import com.lucianoluzzi.login.domain.repository.LoginRepository
import com.lucianoluzzi.login.data.network.request.LoginRequest
import com.lucianoluzzi.login.data.network.response.LoginResponseWrapper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineScopeExtension::class)
class DoLoginUseCaseImplTest {

    private val repository = mock<LoginRepository>()
    private val useCase = DoLoginUseCaseImpl(repository)

    @Test
    fun `verify repository called with Profile when doLogin`() = runBlockingTest {
        val mockProfile = getMockProfile()
        val mockLoginRequest = getMockLoginRequest(mockProfile)

        useCase.doLogin(mockProfile)
        verify(repository).doLogin(mockLoginRequest)
    }

    @Test
    fun `assert useCase return repository result`() = runBlockingTest {
        val expectedReturn = LoginResponseWrapper.Success(Unit)
        val mockProfile = getMockProfile()
        val mockLoginRequest = getMockLoginRequest(mockProfile)

        whenever(repository.doLogin(mockLoginRequest)).thenReturn(expectedReturn)

        val loginResult = useCase.doLogin(mockProfile)
        assertThat(loginResult).isEqualTo(expectedReturn)
    }

    private fun getMockProfile(): com.lucianoluzzi.domain.Profile =
        com.lucianoluzzi.domain.Profile(
            name = "luciano luzzi",
            email = "lucianoluzzi@hotmail.com"
        )

    private fun getMockLoginRequest(profile: com.lucianoluzzi.domain.Profile): LoginRequest = LoginRequest(profile)
}
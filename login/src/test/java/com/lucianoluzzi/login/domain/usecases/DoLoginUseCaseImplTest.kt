package com.lucianoluzzi.login.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.login.CoroutineScopeExtension
import com.lucianoluzzi.login.domain.entities.Profile
import com.lucianoluzzi.login.repository.network.LoginRepository
import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.login.repository.network.response.LoginResponse
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
        val expectedReturn = LoginResponse.Success(Unit)
        val mockProfile = getMockProfile()
        val mockLoginRequest = getMockLoginRequest(mockProfile)

        whenever(repository.doLogin(mockLoginRequest)).thenReturn(expectedReturn)

        val loginResult = useCase.doLogin(mockProfile)
        assertThat(loginResult).isEqualTo(expectedReturn)
    }

    private fun getMockProfile(): Profile = Profile(
        name = "luciano",
        lastName = "luzzi"
    )

    private fun getMockLoginRequest(profile: Profile): LoginRequest = LoginRequest(profile)
}
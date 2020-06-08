package com.lucianoluzzi.login.ui.viewmodel

import com.facebook.AccessToken
import com.facebook.Profile
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.login.CoroutineScopeExtension
import com.lucianoluzzi.login.InstantExecutorExtension
import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.usecases.DoLoginUseCase
import com.lucianoluzzi.login.domain.usecases.GetProfileUseCase
import com.lucianoluzzi.login.repository.network.response.LoginResponse
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineScopeExtension::class, InstantExecutorExtension::class)
class LoginViewModelTest {

    private val convertFacebookProfileUseCase = mock<GetProfileUseCase>()
    private val doLoginUseCase = mock<DoLoginUseCase>()

    private val viewModel = LoginViewModel(
        convertFacebookProfileUseCase = convertFacebookProfileUseCase,
        doLoginUseCase = doLoginUseCase
    )

    @Test
    fun `assert convert profile use case called with mock`() = runBlockingTest {
        val accessToken = mock<AccessToken>()
        val facebookProfile = mock<Profile>()
        val convertedProfile = getConvertedProfile()
        val expectedLoginResult = LoginResponse.Success(Any())

        whenever(
            convertFacebookProfileUseCase.getProfile(facebookProfile, accessToken)
        ).doReturn(convertedProfile)
        whenever(doLoginUseCase.doLogin(convertedProfile)).doReturn(expectedLoginResult)

        viewModel.doLogin(facebookProfile, accessToken)
        assertThat(viewModel.loginResponseState.value).isEqualTo(
            LoginResponseState.Success(
                expectedLoginResult.responseData
            )
        )
    }

    private fun getConvertedProfile(): com.lucianoluzzi.login.domain.entities.Profile {
        return com.lucianoluzzi.login.domain.entities.Profile(
            email = "lucianoluzzi@hotmail.com",
            name = "Luciano",
            lastName = "Luzzi"
        )
    }
}
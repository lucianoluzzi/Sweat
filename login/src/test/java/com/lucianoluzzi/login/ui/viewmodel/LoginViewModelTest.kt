package com.lucianoluzzi.login.ui.viewmodel

import com.facebook.AccessToken
import com.facebook.Profile
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.login.CoroutineScopeExtension
import com.lucianoluzzi.login.InstantExecutorExtension
import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.usecases.DoLoginUseCase
import com.lucianoluzzi.login.domain.usecases.GetProfileUseCase
import com.lucianoluzzi.login.repository.network.response.LoginResponseWrapper
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
        getProfileUseCase = convertFacebookProfileUseCase,
        doLoginUseCase = doLoginUseCase
    )

    @Test
    fun `assert doLoginWithFacebookProfile returns LoginResponse Success`() = runBlockingTest {
        val accessToken = mock<AccessToken>()
        val facebookProfile = mock<Profile>()
        val convertedProfile = getConvertedProfile()
        val expectedLoginResult = LoginResponseWrapper.Success(Any())

        whenever(
            convertFacebookProfileUseCase.getProfile(facebookProfile, accessToken)
        ).doReturn(convertedProfile)
        whenever(doLoginUseCase.doLogin(convertedProfile)).doReturn(expectedLoginResult)

        viewModel.doLoginWithFacebookProfile(facebookProfile, accessToken)
        assertThat(viewModel.loginResponseState.value).isEqualTo(
            LoginResponseState.Success(
                expectedLoginResult.responseData
            )
        )
    }

    @Test
    fun `assert doLoginWithFacebookProfile returns LoginResponse Error`() = runBlockingTest {
        val accessToken = mock<AccessToken>()
        val facebookProfile = mock<Profile>()
        val convertedProfile = getConvertedProfile()
        val expectedLoginResult = LoginResponseWrapper.Error("erro")

        whenever(
            convertFacebookProfileUseCase.getProfile(facebookProfile, accessToken)
        ).doReturn(convertedProfile)
        whenever(doLoginUseCase.doLogin(convertedProfile)).doReturn(expectedLoginResult)

        viewModel.doLoginWithFacebookProfile(facebookProfile, accessToken)
        assertThat(viewModel.loginResponseState.value).isEqualTo(
            LoginResponseState.Error(
                expectedLoginResult.error
            )
        )
    }

    @Test
    fun `assert doLoginWithGoogle returns LoginResponse Success`() = runBlockingTest {
        val googleAccount = mock<GoogleSignInAccount>()
        val convertedProfile = getConvertedProfile()
        val expectedLoginResult = LoginResponseWrapper.Success(Any())

        whenever(
            convertFacebookProfileUseCase.getProfile(googleAccount)
        ).doReturn(convertedProfile)
        whenever(doLoginUseCase.doLogin(convertedProfile)).doReturn(expectedLoginResult)

        viewModel.doLoginWithGoogle(googleAccount)
        assertThat(viewModel.loginResponseState.value).isEqualTo(
            LoginResponseState.Success(
                expectedLoginResult.responseData
            )
        )
    }

    @Test
    fun `assert doLoginWithGoogle returns LoginResponse Error`() = runBlockingTest {
        val googleAccount = mock<GoogleSignInAccount>()
        val convertedProfile = getConvertedProfile()
        val expectedLoginResult = LoginResponseWrapper.Error("erro")

        whenever(
            convertFacebookProfileUseCase.getProfile(googleAccount)
        ).doReturn(convertedProfile)
        whenever(doLoginUseCase.doLogin(convertedProfile)).doReturn(expectedLoginResult)

        viewModel.doLoginWithGoogle(googleAccount)
        assertThat(viewModel.loginResponseState.value).isEqualTo(
            LoginResponseState.Error(
                expectedLoginResult.error
            )
        )
    }

    private fun getConvertedProfile(): com.lucianoluzzi.domain.Profile {
        return com.lucianoluzzi.domain.Profile(
            email = "lucianoluzzi@hotmail.com",
            name = "Luciano",
            lastName = "Luzzi"
        )
    }
}
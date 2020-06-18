package com.lucianoluzzi.login.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.Profile
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.usecases.DoLoginUseCase
import com.lucianoluzzi.login.domain.usecases.GetProfileUseCase
import com.lucianoluzzi.login.repository.network.response.LoginResponseWrapper
import com.lucianoluzzi.networkbuilder.domain.entities.ErrorResponse
import com.lucianoluzzi.utils.doNothing
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val doLoginUseCase: DoLoginUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginResponseState>()
    val loginResponseState: LiveData<LoginResponseState> = _loginState

    fun loginClicked() = doNothing

    fun doLoginWithFacebookProfile(facebookProfile: Profile, accessToken: AccessToken) {
        viewModelScope.launch {
            val convertedProfile = getProfileUseCase.getProfile(
                accessToken = accessToken,
                facebookProfile = facebookProfile
            )

            val loginResponse = doLoginUseCase.doLogin(convertedProfile)
            _loginState.value = getLoginResponseState(loginResponse)
        }
    }

    fun doLoginWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        viewModelScope.launch {
            val convertedProfile = getProfileUseCase.getProfile(googleSignInAccount)
            val loginResponse = doLoginUseCase.doLogin(convertedProfile)
            _loginState.value = getLoginResponseState(loginResponse)
        }
    }

    private fun getLoginResponseState(loginResponseWrapper: LoginResponseWrapper) =
        when (loginResponseWrapper) {
            is LoginResponseWrapper.Error<*> -> {
                LoginResponseState.Error(getErrorModel(loginResponseWrapper))
            }
            is LoginResponseWrapper.Success<*> -> LoginResponseState.Success(getProfile(loginResponseWrapper))
        }

    private fun getProfile(loginResponseWrapper: LoginResponseWrapper.Success<*>): com.lucianoluzzi.domain.Profile {
        return loginResponseWrapper.responseData as com.lucianoluzzi.domain.Profile
    }

    private fun getErrorModel(loginResponseWrapper: LoginResponseWrapper.Error<*>): ErrorResponse {
        return loginResponseWrapper.error?.let {
            return if (it is ErrorResponse)
                it
            else
                ErrorResponse("", 500, "An error has ocurred.")
        } ?: run { ErrorResponse("", 500, "An error has ocurred.") }
    }
}
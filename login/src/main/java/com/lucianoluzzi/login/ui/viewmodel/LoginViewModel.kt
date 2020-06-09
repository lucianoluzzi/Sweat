package com.lucianoluzzi.login.ui.viewmodel

import android.accounts.Account
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.Profile
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.usecases.GetProfileUseCase
import com.lucianoluzzi.login.domain.usecases.DoLoginUseCase
import com.lucianoluzzi.login.repository.network.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val doLoginUseCase: DoLoginUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginResponseState>()
    val loginResponseState: LiveData<LoginResponseState> = _loginState

    fun doLoginWithFacebookProfile(facebookProfile: Profile, accessToken: AccessToken) {
        viewModelScope.launch {
            _loginState.value = LoginResponseState.Loading

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
            _loginState.value = LoginResponseState.Loading

            val convertedProfile = getProfileUseCase.getProfile(googleSignInAccount)
            val loginResponse = doLoginUseCase.doLogin(convertedProfile)
            _loginState.value = getLoginResponseState(loginResponse)
        }
    }

    private fun getLoginResponseState(loginResponse: LoginResponse) =
        when (loginResponse) {
            is LoginResponse.Error<*> -> LoginResponseState.Error(loginResponse.error)
            is LoginResponse.Success<*> -> LoginResponseState.Success(loginResponse.responseData)
        }
}
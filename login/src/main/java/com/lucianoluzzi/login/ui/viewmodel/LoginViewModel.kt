package com.lucianoluzzi.login.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.Profile
import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.usecases.GetProfileUseCase
import com.lucianoluzzi.login.domain.usecases.DoLoginUseCase
import com.lucianoluzzi.login.repository.network.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val convertFacebookProfileUseCase: GetProfileUseCase,
    private val doLoginUseCase: DoLoginUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginResponseState>()
    val loginResponseState: LiveData<LoginResponseState> = _loginState

    fun doLogin(facebookProfile: Profile, accessToken: AccessToken) {
        viewModelScope.launch {
            _loginState.value = LoginResponseState.Loading

            val convertedProfile = convertFacebookProfileUseCase.getProfile(
                accessToken = accessToken,
                facebookProfile = facebookProfile
            )
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
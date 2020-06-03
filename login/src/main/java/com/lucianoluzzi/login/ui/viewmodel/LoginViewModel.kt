package com.lucianoluzzi.login.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.Profile
import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.usecases.ConvertFacebookProfileUseCase
import com.lucianoluzzi.login.domain.usecases.DoLoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val convertFacebookProfileUseCase: ConvertFacebookProfileUseCase,
    private val doLoginUseCase: DoLoginUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginResponseState>()
    val loginResponseState: LiveData<LoginResponseState> = _loginState

    fun doLogin(profile: Profile, accessToken: AccessToken) {
        viewModelScope.launch {
            _loginState.value = LoginResponseState.Loading
            val convertedProfile = convertFacebookProfileUseCase.convertFacebookProfile(
                profile = profile,
                accessToken = accessToken
            )
            _loginState.value = doLoginUseCase.doLogin(convertedProfile)
        }
    }
}
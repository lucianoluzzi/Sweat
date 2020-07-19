package com.lucianoluzzi.login.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.Profile
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.usecases.GetProfileUseCase
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginResponseState>()
    val loginResponseState: LiveData<LoginResponseState> = _loginState

    fun doLogin(googleAccount: GoogleSignInAccount) {
        viewModelScope.launch {
            val profile = getProfileUseCase.getProfile(googleAccount)
            _loginState.value = LoginResponseState.Success(
                com.lucianoluzzi.domain.Profile(
                    name = profile.name,
                    email = profile.email
                )
            )
        }
    }

    fun doLogin(facebookProfile: Profile, accessToken: AccessToken) {
        viewModelScope.launch {
            val convertedProfile = getProfileUseCase.getProfile(
                accessToken = accessToken,
                facebookProfile = facebookProfile
            )

            _loginState.value = LoginResponseState.Success(
                com.lucianoluzzi.domain.Profile(
                    name = convertedProfile.name,
                    email = convertedProfile.email
                )
            )
        }
    }
}
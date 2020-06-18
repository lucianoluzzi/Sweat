package com.lucianoluzzi.login.domain.usecases

import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.login.repository.network.response.LoginResponseWrapper

interface DoLoginUseCase {
    suspend fun doLogin(profile: Profile): LoginResponseWrapper
}
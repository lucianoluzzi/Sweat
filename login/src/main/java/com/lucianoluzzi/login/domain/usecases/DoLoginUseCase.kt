package com.lucianoluzzi.login.domain.usecases

import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.login.repository.network.response.LoginResponse

interface DoLoginUseCase {
    suspend fun doLogin(profile: com.lucianoluzzi.domain.Profile): LoginResponse
}
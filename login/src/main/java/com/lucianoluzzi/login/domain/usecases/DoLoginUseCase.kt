package com.lucianoluzzi.login.domain.usecases

import com.lucianoluzzi.login.domain.entities.Profile
import com.lucianoluzzi.login.repository.network.response.LoginResponse

interface DoLoginUseCase {
    suspend fun doLogin(profile: Profile): LoginResponse
}
package com.lucianoluzzi.login.domain.usecases

import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.entities.Profile

interface DoLoginUseCase {
    suspend fun doLogin(profile: Profile): LoginResponseState
}
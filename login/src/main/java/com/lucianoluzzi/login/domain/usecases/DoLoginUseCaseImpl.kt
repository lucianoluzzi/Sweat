package com.lucianoluzzi.login.domain.usecases

import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.entities.Profile
import com.lucianoluzzi.login.repository.network.LoginRepository
import com.lucianoluzzi.login.repository.network.request.LoginRequest

class DoLoginUseCaseImpl(private val loginRepository: LoginRepository): DoLoginUseCase {

    override suspend fun doLogin(profile: Profile): LoginResponseState =
        loginRepository.doLogin(
            LoginRequest(
                profile
            )
        )
}
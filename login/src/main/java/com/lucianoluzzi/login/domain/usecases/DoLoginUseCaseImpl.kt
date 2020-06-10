package com.lucianoluzzi.login.domain.usecases

import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.login.repository.network.LoginRepository
import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.login.repository.network.response.LoginResponse
import com.lucianoluzzi.tests.DispatcherRegistry
import kotlinx.coroutines.withContext

class DoLoginUseCaseImpl(private val loginRepository: LoginRepository): DoLoginUseCase {

    override suspend fun doLogin(profile: com.lucianoluzzi.domain.Profile): LoginResponse =
        withContext(DispatcherRegistry.IO) {
            loginRepository.doLogin(
                LoginRequest(
                    profile
                )
            )
        }
}
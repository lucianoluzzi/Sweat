package com.lucianoluzzi.login.domain.usecases

import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.login.repository.network.LoginRepository
import com.lucianoluzzi.login.repository.network.request.LoginRequest
import com.lucianoluzzi.login.repository.network.response.LoginResponseWrapper
import com.lucianoluzzi.tests.DispatcherRegistry
import kotlinx.coroutines.withContext

class DoLoginUseCaseImpl(private val loginRepository: LoginRepository): DoLoginUseCase {

    override suspend fun doLogin(profile: Profile): LoginResponseWrapper =
        withContext(DispatcherRegistry.IO) {
            loginRepository.doLogin(
                LoginRequest(
                    profile
                )
            )
        }
}
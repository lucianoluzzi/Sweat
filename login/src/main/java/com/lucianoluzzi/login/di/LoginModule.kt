package com.lucianoluzzi.login.di

import com.lucianoluzzi.login.domain.usecases.ConvertFacebookProfileUseCaseImpl
import com.lucianoluzzi.login.domain.usecases.DoLoginUseCaseImpl
import com.lucianoluzzi.login.repository.network.FacebookRepositoryImpl
import com.lucianoluzzi.login.repository.network.LoginRepositoryImpl
import com.lucianoluzzi.login.ui.LoginFragmentFactory
import com.lucianoluzzi.login.ui.viewmodel.LoginViewModel
import com.lucianoluzzi.networkbuilder.APIProvider
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LoginModule {
    val module = module {

        viewModel {
            val convertFacebookProfileUseCase =
                ConvertFacebookProfileUseCaseImpl(FacebookRepositoryImpl())
            val doLoginUseCase = DoLoginUseCaseImpl(LoginRepositoryImpl(get() as APIProvider))

            LoginViewModel(
                convertFacebookProfileUseCase = convertFacebookProfileUseCase,
                doLoginUseCase = doLoginUseCase
            )
        }

        factory {
            LoginFragmentFactory(get() as LoginViewModel)
        }
    }
}
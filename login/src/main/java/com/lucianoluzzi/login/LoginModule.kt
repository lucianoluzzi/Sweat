package com.lucianoluzzi.login

import com.lucianoluzzi.analytics.AnalyticsTracker
import com.lucianoluzzi.login.data.analytics.LoginTracker
import com.lucianoluzzi.login.data.network.FacebookRepository
import com.lucianoluzzi.login.data.network.LoginRepositoryImpl
import com.lucianoluzzi.login.domain.usecases.DoLoginUseCaseImpl
import com.lucianoluzzi.login.domain.usecases.GetProfileUseCaseImpl
import com.lucianoluzzi.login.ui.LoginFragmentFactory
import com.lucianoluzzi.login.ui.viewmodel.LoginViewModel
import com.lucianoluzzi.networkbuilder.APIProvider
import com.lucianoluzzi.networkbuilder.NetworkExecutor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LoginModule {
    val module = module {

        viewModel {
            val getProfileUseCase =
                GetProfileUseCaseImpl(FacebookRepository())
            val doLoginUseCase = DoLoginUseCaseImpl(
                LoginRepositoryImpl(get() as APIProvider, get() as NetworkExecutor)
            )

            LoginViewModel(
                getProfileUseCase = getProfileUseCase,
                doLoginUseCase = doLoginUseCase
            )
        }

        factory {
            LoginTracker(get() as AnalyticsTracker)
        }

        factory {
            LoginFragmentFactory(
                get() as LoginViewModel,
                get() as LoginTracker
            )
        }
    }
}
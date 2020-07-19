package com.lucianoluzzi.login

import com.lucianoluzzi.analytics.AnalyticsTracker
import com.lucianoluzzi.login.data.analytics.LoginTracker
import com.lucianoluzzi.login.data.network.FacebookRepository
import com.lucianoluzzi.login.data.network.LoginRepositoryImpl
import com.lucianoluzzi.login.domain.usecases.DoLoginUseCaseImpl
import com.lucianoluzzi.login.domain.usecases.GetProfileUseCase
import com.lucianoluzzi.login.domain.usecases.GetProfileUseCaseImpl
import com.lucianoluzzi.login.ui.LoginFragmentFactory
import com.lucianoluzzi.login.ui.viewmodel.LoginViewModel
import com.lucianoluzzi.login.ui.viewmodel.SplashScreenViewModel
import com.lucianoluzzi.networkbuilder.APIProvider
import com.lucianoluzzi.networkbuilder.NetworkExecutor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LoginModule {
    val module = module {

        factory<GetProfileUseCase> {
            GetProfileUseCaseImpl(FacebookRepository())
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

        viewModel {
            val doLoginUseCase = DoLoginUseCaseImpl(
                LoginRepositoryImpl(get() as APIProvider, get() as NetworkExecutor)
            )

            LoginViewModel(
                getProfileUseCase = get() as GetProfileUseCase,
                doLoginUseCase = doLoginUseCase
            )
        }

        viewModel {
            SplashScreenViewModel(get() as GetProfileUseCase)
        }
    }
}
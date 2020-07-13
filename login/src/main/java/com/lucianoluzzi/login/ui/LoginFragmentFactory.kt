package com.lucianoluzzi.login.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.lucianoluzzi.login.data.analytics.LoginTracker
import com.lucianoluzzi.login.ui.viewmodel.LoginViewModel

class LoginFragmentFactory(
    private val viewModel: LoginViewModel,
    private val loginTracker: LoginTracker
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            LoginFragment::class.java.name -> LoginFragment(viewModel, loginTracker)
            else -> super.instantiate(classLoader, className)
        }
    }
}
package com.lucianoluzzi.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.login.R
import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.entities.facebook.FacebookSessionManager
import com.lucianoluzzi.login.domain.entities.google.GoogleSessionManager
import com.lucianoluzzi.login.ui.viewmodel.SplashScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {
    private val loginFragmentFactory: LoginFragmentFactory = get()

    private val viewModel: SplashScreenViewModel by viewModel()

    private val googleSessionManager by lazy {
        GoogleSessionManager(
            this
        )
    }
    private val facebookSessionManager by lazy {
        FacebookSessionManager()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = loginFragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        viewModel.loginResponseState.observe(this, Observer {
            handleLoginResponse(it)
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.viewModelScope.launch {
            delay(1500)

            val googleAccount = googleSessionManager.getSignedInAccount()
            when {
                googleAccount != null -> {
                    viewModel.doLogin(googleAccount)
                }
                facebookSessionManager.isSignedIn() -> {
                    viewModel.doLogin(
                        facebookSessionManager.profile!!,
                        facebookSessionManager.accessToken!!
                    )
                }
                else -> {
                    navigateToLogin()
                }
            }
        }
    }

    private fun handleLoginResponse(loginResponseState: LoginResponseState) {
        when (loginResponseState) {
            is LoginResponseState.Success -> {
                navigateToUserFeed(loginResponseState.response)
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent().apply {
            setClassName(this@SplashScreenActivity, "com.lucianoluzzi.login.ui.LoginActivity")
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    private fun navigateToUserFeed(profile: Profile) {
        val intent = Intent()
        intent.putExtra("PROFILE", profile)
        intent.setClassName(this, "com.lucianoluzzi.workout.feed.ui.FeedActivity")
        startActivity(intent)
    }
}
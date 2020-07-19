package com.lucianoluzzi.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.login.R
import com.lucianoluzzi.login.domain.entities.LoginResponseState
import com.lucianoluzzi.login.domain.entities.facebook.FacebookSessionManager
import com.lucianoluzzi.login.domain.entities.google.GoogleSessionManager
import com.lucianoluzzi.login.ui.viewmodel.SplashScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel

class SplashScreenFragment : Fragment() {

    private val viewModel: SplashScreenViewModel by viewModel()

    private val googleSessionManager by lazy {
        GoogleSessionManager(
            requireContext()
        )
    }
    private val facebookSessionManager by lazy {
        FacebookSessionManager()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.loginResponseState.observe(viewLifecycleOwner, Observer {
            handleLoginResponse(it)
        })
        return inflater.inflate(R.layout.fragment_splashscreen, container, false)
    }

    override fun onResume() {
        super.onResume()
        setFullScreen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                viewModel.viewModelScope.launch {
                    delay(3000)

                    withContext(Dispatchers.Main) {
                        removeFullScreen()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, LoginFragment::class.java, null)
                            .commit()
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        removeFullScreen()
    }

    private fun handleLoginResponse(loginResponseState: LoginResponseState) {
        when (loginResponseState) {
            is LoginResponseState.Success -> {
                navigateToUserFeed(loginResponseState.response)
            }
        }
    }

    private fun navigateToUserFeed(profile: Profile) {
        val intent = Intent()
        intent.putExtra("PROFILE", profile)
        intent.setClassName(requireContext(), "com.lucianoluzzi.workout.feed.ui.FeedActivity")
        startActivity(intent)
    }

    private fun setFullScreen() {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun removeFullScreen() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}
package com.lucianoluzzi.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.Profile
import com.facebook.ProfileTracker
import com.lucianoluzzi.login.databinding.FragmentLoginBinding
import com.lucianoluzzi.login.domain.entities.facebook.Permissions
import com.lucianoluzzi.login.ui.extensions.onLogin
import com.lucianoluzzi.login.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch


class LoginFragment(private val viewModel: LoginViewModel) : Fragment() {
    private val binding by lazy {
        val inflater = LayoutInflater.from(requireContext())
        FragmentLoginBinding.inflate(inflater)
    }

    private val facebookCallbackManager by lazy {
        CallbackManager.Factory.create()
    }

    private lateinit var profileTracker: ProfileTracker

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFacebookProfileTracker()
        setFacebookLoginButton()

        return binding.root
    }

    private fun setFacebookProfileTracker() {
        profileTracker = object : ProfileTracker() {
            override fun onCurrentProfileChanged(
                oldProfile: Profile?,
                currentProfile: Profile?
            ) {
                currentProfile?.let {
                    viewModel.doLogin(currentProfile, AccessToken.getCurrentAccessToken())
                }
            }
        }
    }

    private fun setFacebookLoginButton() {
        binding.facebookLogin.setPermissions(Permissions().listOfPermissions())
        binding.facebookLogin.fragment = this
        lifecycleScope.launch {
            binding.facebookLogin.onLogin(facebookCallbackManager)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        profileTracker.stopTracking()
    }
}
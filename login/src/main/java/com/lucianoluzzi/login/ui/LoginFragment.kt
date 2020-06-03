package com.lucianoluzzi.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.facebook.CallbackManager
import com.facebook.Profile
import com.lucianoluzzi.login.databinding.FragmentLoginBinding
import com.lucianoluzzi.login.domain.entities.facebook.Permissions
import com.lucianoluzzi.login.ui.extensions.onLogin
import com.lucianoluzzi.login.ui.uimodel.FacebookLoginResponseState
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFacebookLoginButton()
        return binding.root
    }

    private fun setFacebookLoginButton() {
        binding.facebookLogin.setPermissions(Permissions().listOfPermissions())
        binding.facebookLogin.fragment = this
        lifecycleScope.launch {
            val result = binding.facebookLogin.onLogin(facebookCallbackManager)
            onFacebookResult(result)
        }
    }

    private fun onFacebookResult(response: FacebookLoginResponseState) {
        when (response) {
            is FacebookLoginResponseState.Cancel -> { Toast.makeText(requireContext(), "cancel", Toast.LENGTH_LONG).show() }
            is FacebookLoginResponseState.Success<*> -> {
                Toast.makeText(requireContext(), "success", Toast.LENGTH_LONG).show()
                val currentProfile = Profile.getCurrentProfile()
                response.response?.let {
                    viewModel.doLogin(
                        profile = currentProfile,
                        accessToken = it.accessToken
                    )
                }
            }
            is FacebookLoginResponseState.Error<*> -> { Toast.makeText(requireContext(), "error", Toast.LENGTH_LONG).show() }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
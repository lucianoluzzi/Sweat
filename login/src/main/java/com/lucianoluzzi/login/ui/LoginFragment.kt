package com.lucianoluzzi.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.Profile
import com.facebook.ProfileTracker
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
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
    private val googleSignInClient by lazy {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        GoogleSignIn.getClient(requireActivity(), googleSignInOptions)
    }

    private lateinit var profileTracker: ProfileTracker

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.signInButton.setSize(SignInButton.SIZE_STANDARD)
        setFacebookProfileTracker()
        setFacebookLoginButton()
        setGoogleLoginButton()

        return binding.root
    }

    private fun setFacebookProfileTracker() {
        profileTracker = object : ProfileTracker() {
            override fun onCurrentProfileChanged(
                oldProfile: Profile?,
                currentProfile: Profile?
            ) {
                currentProfile?.let {
                    viewModel.doLoginWithFacebookProfile(currentProfile, AccessToken.getCurrentAccessToken())
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

    private fun setGoogleLoginButton() {
        binding.signInButton.setOnClickListener {
            val signInIntent: Intent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGNIN_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGNIN_REQUEST_CODE) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val result = completedTask.getResult(ApiException::class.java)
            result?.let {
                viewModel.doLoginWithGoogle(it)
            }
        } catch (e: ApiException) {
            Log.w("GOOGLE_SIGN_IN", "signInResult:failed code=" + e.statusCode)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        profileTracker.stopTracking()
    }

    private companion object {
        const val GOOGLE_SIGNIN_REQUEST_CODE = 197
    }
}
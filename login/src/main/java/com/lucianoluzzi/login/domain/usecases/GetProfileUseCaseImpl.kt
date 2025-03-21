package com.lucianoluzzi.login.domain.usecases

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.login.data.network.FacebookRepository
import com.lucianoluzzi.tests.DispatcherRegistry
import kotlinx.coroutines.withContext

class GetProfileUseCaseImpl(private val facebookRepository: FacebookRepository) :
    GetProfileUseCase {

    override suspend fun getProfile(
        facebookProfile: com.facebook.Profile,
        accessToken: AccessToken
    ): Profile = withContext(DispatcherRegistry.IO) {
        val email = facebookRepository.getEmail(accessToken)

        return@withContext email?.let {
            val middleName = facebookProfile.middleName?.let {
                " $it "
            } ?: " "

            Profile(
                email = it,
                name = "${facebookProfile.firstName}$middleName${facebookProfile.lastName}",
                imageUrl = facebookProfile.getProfilePictureUri(200, 200)?.toString()
            )
        } ?: run {
            throw Exception("Email could not be retrieved, please try again later or with a different account.")
        }
    }

    override suspend fun getProfile(googleSignInAccount: GoogleSignInAccount): Profile {
        if (googleSignInAccount.email != null &&
            googleSignInAccount.givenName != null &&
            googleSignInAccount.familyName != null
        ) {
            return Profile(
                email = googleSignInAccount.email!!,
                name = "${googleSignInAccount.givenName!!} ${googleSignInAccount.familyName!!}",
                imageUrl = googleSignInAccount.photoUrl?.toString()
            )
        } else
            throw Exception("Email could not be retrieved, please try again later or with a different account.")
    }
}
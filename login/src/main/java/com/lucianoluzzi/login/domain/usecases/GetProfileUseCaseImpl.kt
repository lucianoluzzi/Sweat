package com.lucianoluzzi.login.domain.usecases

import com.facebook.AccessToken
import com.lucianoluzzi.login.domain.entities.Profile
import com.lucianoluzzi.login.repository.network.FacebookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProfileUseCaseImpl(private val facebookRepository: FacebookRepository) :
    GetProfileUseCase {

    override suspend fun getProfile(
        facebookProfile: com.facebook.Profile,
        accessToken: AccessToken
    ): Profile = withContext(Dispatchers.IO) {
        val email = facebookRepository.getEmail(accessToken)

        return@withContext Profile(
            email = email,
            name = facebookProfile.firstName,
            middleName = facebookProfile.middleName,
            lastName = facebookProfile.lastName,
            profilePicture = facebookProfile.getProfilePictureUri(200, 200)
        )
    }

    private companion object {
        const val EMAIL_JSON_KEY = "email"
        const val FIRST_NAME = "first_name"
        const val MIDDLE_NAME = "middle_name"
        const val LAST_NAME = "last_name"
        const val PROFILE_PICTURE = "profile_pic"
    }
}
package com.lucianoluzzi.login.domain.usecases

import com.facebook.AccessToken
import com.lucianoluzzi.login.domain.entities.Profile
import com.lucianoluzzi.login.repository.network.FacebookRepository

class ConvertFacebookProfileUseCaseImpl(private val facebookRepository: FacebookRepository) :
    ConvertFacebookProfileUseCase {

    override suspend fun convertFacebookProfile(
        profile: com.facebook.Profile,
        accessToken: AccessToken
    ): Profile {
        val email = facebookRepository.getEmail(accessToken)
        return Profile(
            email = email,
            name = profile.firstName,
            middleName = profile.middleName,
            lastName = profile.lastName,
            profilePicture = profile.getProfilePictureUri(200, 200)
        )
    }
}
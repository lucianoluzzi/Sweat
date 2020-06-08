package com.lucianoluzzi.login.domain.usecases

import com.facebook.AccessToken
import com.lucianoluzzi.login.domain.entities.Profile
import com.lucianoluzzi.login.repository.network.FacebookRepository
import com.lucianoluzzi.tests.DispatcherRegistry
import kotlinx.coroutines.withContext

class GetProfileUseCaseImpl(private val facebookRepository: FacebookRepository) :
    GetProfileUseCase {

    override suspend fun getProfile(
        facebookProfile: com.facebook.Profile,
        accessToken: AccessToken
    ): Profile = withContext(DispatcherRegistry.IO) {
        val email = facebookRepository.getEmail(accessToken)

        return@withContext Profile(
            email = email,
            name = facebookProfile.firstName,
            middleName = facebookProfile.middleName,
            lastName = facebookProfile.lastName,
            imageUrl = facebookProfile.getProfilePictureUri(200, 200)?.toString()
        )
    }
}
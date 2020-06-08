package com.lucianoluzzi.login.domain.usecases

import com.facebook.AccessToken
import com.facebook.Profile
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.login.CoroutineScopeExtension
import com.lucianoluzzi.login.repository.network.FacebookRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineScopeExtension::class)
class GetProfileUseCaseImplTest {

    private val accessToken = mock<AccessToken>()
    private val facebookProfile = mock<Profile> {
        on { firstName } doReturn "Luciano"
        on { lastName } doReturn "Luzzi"
        on { middleName } doReturn null
        on { getProfilePictureUri(200,200) } doReturn null
    }
    private val repository = mock<FacebookRepository>()
    private val useCase = GetProfileUseCaseImpl(repository)

    @Test
    fun `assert Profile when doLogin`() = runBlockingTest {
        whenever(repository.getEmail(accessToken)).doReturn("lucianoluzzi@hotmail.com")

        val expectedProfile = com.lucianoluzzi.login.domain.entities.Profile (
            email = "lucianoluzzi@hotmail.com",
            name = "Luciano",
            lastName = "Luzzi"
        )
        val returnedProfile = useCase.getProfile(facebookProfile, accessToken)

        assertThat(expectedProfile).isEqualTo(returnedProfile)
    }
}
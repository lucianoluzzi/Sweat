package com.lucianoluzzi.login.domain.usecases

import android.net.Uri
import com.facebook.AccessToken
import com.facebook.Profile
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.common.truth.Truth.assertThat
import com.lucianoluzzi.login.CoroutineScopeExtension
import com.lucianoluzzi.login.repository.network.FacebookRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(CoroutineScopeExtension::class)
class GetProfileUseCaseImplTest {

    private val repository = mock<FacebookRepository>()
    private val useCase = GetProfileUseCaseImpl(repository)

    @Test
    fun `assert Profile when doLogin with facebook profile`() = runBlockingTest {
        val accessToken = mock<AccessToken>()
        val facebookProfile = mock<Profile> {
            on { firstName } doReturn "Luciano"
            on { lastName } doReturn "Luzzi"
            on { middleName } doReturn null
            on { getProfilePictureUri(200,200) } doReturn null
        }
        whenever(repository.getEmail(accessToken)).doReturn("lucianoluzzi@hotmail.com")

        val expectedProfile = com.lucianoluzzi.login.domain.entities.Profile (
            email = "lucianoluzzi@hotmail.com",
            name = "Luciano",
            lastName = "Luzzi"
        )
        val returnedProfile = useCase.getProfile(facebookProfile, accessToken)

        assertThat(expectedProfile).isEqualTo(returnedProfile)
    }

    @Test
    fun `assert exception thrown when repository return null email`() = runBlockingTest {
        val accessToken = mock<AccessToken>()
        val facebookProfile = mock<Profile> {
            on { firstName } doReturn "Luciano"
            on { lastName } doReturn "Luzzi"
            on { middleName } doReturn null
            on { getProfilePictureUri(200,200) } doReturn null
        }
        whenever(repository.getEmail(accessToken)).doReturn(null)

        assertThrows<Exception> {
            useCase.getProfile(facebookProfile, accessToken)
        }
    }

    @Test
    fun `assert Profile when doLogin with google account`() = runBlockingTest {
        val mockedUri = mock<Uri> {
            on { toString() } doReturn "www.google.com"
        }
        val googleAccount = mock<GoogleSignInAccount> {
            on { email } doReturn "lucianoluzzi@hotmail.com"
            on { givenName } doReturn "Luciano"
            on { familyName } doReturn "Luzzi"
            on { photoUrl } doReturn mockedUri
        }
        val expectedProfile = com.lucianoluzzi.login.domain.entities.Profile (
            email = "lucianoluzzi@hotmail.com",
            name = "Luciano",
            lastName = "Luzzi",
            imageUrl = "www.google.com"
        )

        val returnedProfile = useCase.getProfile(googleAccount)

        assertThat(expectedProfile).isEqualTo(returnedProfile)
    }

    @Test
    fun `assert throws exception when doLogin with google account without email`() = runBlockingTest {
        val googleAccount = mock<GoogleSignInAccount> {
            on { email } doReturn null
            on { givenName } doReturn "Luciano"
            on { familyName } doReturn "Luzzi"
        }

        assertThrows<Exception> {
            useCase.getProfile(googleAccount)
        }
    }

    @Test
    fun `assert throws exception when doLogin with google account without name`() = runBlockingTest {
        val googleAccount = mock<GoogleSignInAccount> {
            on { email } doReturn "lucianoluzzi@hotmail.com"
            on { givenName } doReturn null
            on { familyName } doReturn "Luzzi"
        }

        assertThrows<Exception> {
            useCase.getProfile(googleAccount)
        }
    }

    @Test
    fun `assert throws exception when doLogin with google account without last name`() = runBlockingTest {
        val googleAccount = mock<GoogleSignInAccount> {
            on { email } doReturn "lucianoluzzi@hotmail.com"
            on { givenName } doReturn "Luciano"
            on { familyName } doReturn null
        }

        assertThrows<Exception> {
            useCase.getProfile(googleAccount)
        }
    }
}
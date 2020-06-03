package com.lucianoluzzi.login.domain.entities.facebook

class Permissions {
    fun listOfPermissions() = listOf(EMAIL, PUBLIC_PROFILE)

    private companion object {
        const val EMAIL = "email"
        const val PUBLIC_PROFILE = "public_profile"
    }
}
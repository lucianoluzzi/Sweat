package com.lucianoluzzi.login.domain.entities

import android.net.Uri

data class Profile (
    private val email: String?,
    private val name: String,
    private val middleName: String?,
    private val lastName: String,
    private val profilePicture: Uri?
)
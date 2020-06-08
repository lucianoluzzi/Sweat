package com.lucianoluzzi.login.domain.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Profile(
    val email: String? = null,
    val name: String,
    val middleName: String? = null,
    val lastName: String,
    val imageUrl: String? = null,
    val birthDate: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val age: Int? = null
)
package com.lucianoluzzi.domain

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Profile(
    val email: String,
    val name: String,
    val imageUrl: String? = null,
    val birthDate: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val age: Int? = null
) : Serializable
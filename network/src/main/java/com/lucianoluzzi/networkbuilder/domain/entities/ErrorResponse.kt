package com.lucianoluzzi.networkbuilder.domain.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse (
    val url: String,
    val code: Int,
    val message: String
)
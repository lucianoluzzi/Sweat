package com.lucianoluzzi.login.repository.network.request

import com.lucianoluzzi.login.domain.entities.Profile

data class LoginRequest(private val profile: Profile)
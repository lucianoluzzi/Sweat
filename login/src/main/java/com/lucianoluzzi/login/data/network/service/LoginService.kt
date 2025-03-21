package com.lucianoluzzi.login.data.network.service

import com.lucianoluzzi.domain.Profile
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @Headers("Accept: application/json")
    @POST("persons")
    suspend fun doLogin(@Body profile: Profile): Profile
}
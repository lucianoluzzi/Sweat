package com.lucianoluzzi.networkbuilder

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test


class NetworkBuilderTest {

    @Test
    fun `assert baseUrl when build`() {
        val returnedBaseUrl = NetworkBuilder().build().baseUrl().toString()
        val expectedBaseUrl = "https://api.github.com/"

        assertThat(returnedBaseUrl).isEqualTo(expectedBaseUrl)
    }
}
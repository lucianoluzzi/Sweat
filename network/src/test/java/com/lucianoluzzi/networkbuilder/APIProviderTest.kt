package com.lucianoluzzi.networkbuilder

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import retrofit2.Retrofit

class APIProviderTest {
    val retrofit = mock<Retrofit>()

    @Test
    fun `verify retrofit called with correct class when createService`() {
        APIProvider(retrofit).createService(MockInterface::class.java)
        verify(retrofit).create(MockInterface::class.java)
    }

    private interface MockInterface
}
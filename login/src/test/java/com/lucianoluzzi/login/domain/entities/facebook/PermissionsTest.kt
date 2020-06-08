package com.lucianoluzzi.login.domain.entities.facebook

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class PermissionsTest {

    @Test
    fun `assert correct list when getPermissions`() {
        val returnedPermissions = Permissions().listOfPermissions()
        assertThat(returnedPermissions).containsExactly("email", "public_profile")
    }
}
package com.brasil.transparente.api.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class SmokeTestControllerTest {

    @Test
    fun returnsTheSmokeSuccessMessage() {
        assertEquals("[SMOKE SUCCESSFUL]", SmokeTestController().smokeTest())
    }
}
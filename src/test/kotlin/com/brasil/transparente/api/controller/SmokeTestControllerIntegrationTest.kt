package com.brasil.transparente.api.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class SmokeTestControllerIntegrationTest {

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(SmokeTestController()).build()
    }

    @Test
    fun shouldReturnSmokeSuccessMessage() {
        mockMvc.perform(get("/smoke"))
            .andExpect(status().isOk)
            .andExpect(content().string("[SMOKE SUCCESSFUL]"))
    }
}

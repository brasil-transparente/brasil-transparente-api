package com.brasil.transparente.api.controller

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Hidden
@RestController
class SmokeTestController {

    @GetMapping("/smoke")
    fun smokeTest() : String {
        return "[SMOKE SUCCESSFUL]"
    }

}
package com.brasil.transparente.api.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
public class SmokeTestController {

    @GetMapping("/smoke")
    public String smokeTest() {
        return "[SMOKE SUCCESSFUL]";
    }

}

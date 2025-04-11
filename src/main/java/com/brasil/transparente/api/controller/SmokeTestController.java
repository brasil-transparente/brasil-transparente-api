package com.brasil.transparente.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmokeTestController {

    @GetMapping("/smoke")
    public String smokeTest() {
        return "[SMOKE SUCCESSFUL]";
    }

}

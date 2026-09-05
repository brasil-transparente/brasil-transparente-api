package com.brasil.transparente.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BrasilTransparenteApiApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<BrasilTransparenteApiApplication>(*args)
        }
    }
}

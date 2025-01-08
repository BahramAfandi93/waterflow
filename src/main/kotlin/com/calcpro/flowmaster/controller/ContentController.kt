package com.calcpro.flowmaster.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ContentController {
    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/reg/signup")
    fun registration(): String {
        return "signup"
    }
}
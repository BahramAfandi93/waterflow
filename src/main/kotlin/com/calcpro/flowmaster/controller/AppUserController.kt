package com.calcpro.flowmaster.controller

import com.calcpro.flowmaster.service.UserService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class AppUserController(
    private val userService: UserService
) {

    @GetMapping("/user")
    fun user(@AuthenticationPrincipal oauth2User: OAuth2User): Map<String, Any?> {
        return mapOf(
            "name" to oauth2User.getAttribute("name"),
            "email" to oauth2User.getAttribute("email")
        )
    }

    @GetMapping("/")
    fun login(): String {
        return "login" // Render login.html
    }

    @GetMapping("/dashboard")
    fun dashboard(@AuthenticationPrincipal oAuth2User: OAuth2User, model: Model): String {
        model.addAttribute("name", oAuth2User.getAttribute("name"))
        model.addAttribute("email", oAuth2User.getAttribute("email"))
        return "dashboard" // Render dashboard.html
    }
}
package com.calcpro.flowmaster.controller

import com.calcpro.flowmaster.dto.UserRequestDto
import com.calcpro.flowmaster.logger.DPLogger
import com.calcpro.flowmaster.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    @PostMapping("/add-new-user")
    fun saveNewUser(@RequestBody userRequest: UserRequestDto) = userService.addNewUser(userRequest)

    @GetMapping("/get-user/id/{id}")
    fun getUserById(@PathVariable id: Long) = userService.getUserById(id)
}
package com.calcpro.flowmaster.dto

import com.calcpro.flowmaster.dao.entity.Role
import java.time.LocalDate

data class UserRequestDto(
    val name: String?,
    val lastname: String?,
    val email: String?,
    val password: String?,
    val birthday: LocalDate?,
    val position: String? = null,
    val company: String? = null,
    val phone: String? = null,
    val role: Role? = null,
)

data class UserResponse(
    val name: String?,
    val lastname: String?,
    val email: String?,
    val password: String?,
    val birthday: LocalDate?,
    val position: String? = null,
    val company: String? = null,
    val phone: String? = null,
    val role: Role? = null,
)
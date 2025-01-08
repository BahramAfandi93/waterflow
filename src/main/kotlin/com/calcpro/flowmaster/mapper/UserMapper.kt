package com.calcpro.flowmaster.mapper

import com.calcpro.flowmaster.dao.entity.Role.ENGINEER
import com.calcpro.flowmaster.dao.entity.AppUser
import com.calcpro.flowmaster.dto.UserRequestDto
import com.calcpro.flowmaster.dto.UserResponse
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun userToUserRequestDto(appUser: AppUser) = UserRequestDto(
        name = appUser.name,
        lastname = appUser.email,
        email = appUser.email,
        password = appUser.password,
        birthday = appUser.birthday,
        position = appUser.position,
        company = appUser.company,
        phone = appUser.phone,
        role = appUser.role ?: ENGINEER
    )

    fun userRequestDtoToUser(userRequestDto: UserRequestDto) = AppUser(
        name = userRequestDto.name,
        lastname = userRequestDto.lastname,
        email = userRequestDto.email,
        password = userRequestDto.password,
        birthday = userRequestDto.birthday,
        position = userRequestDto.position,
        company = userRequestDto.company,
        phone = userRequestDto.phone,
        role = userRequestDto.role ?: ENGINEER
    )

    fun userToUserResponse(engineer: AppUser) = UserResponse(
        name = engineer.name,
        lastname = engineer.lastname,
        email = engineer.email,
        password = engineer.password,
        birthday = engineer.birthday,
        position = engineer.position,
        company = engineer.company,
        phone = engineer.phone,
        role = engineer.role ?: ENGINEER
    )
}
package com.calcpro.flowmaster.mapper

import com.calcpro.flowmaster.dao.entity.User
import com.calcpro.flowmaster.dao.entity.Role.USER
import com.calcpro.flowmaster.dto.UserRequestDto
import com.calcpro.flowmaster.dto.UserResponse
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun userToUserRequestDto(user: User) = UserRequestDto(
        name = user.name,
        lastname = user.email,
        email = user.email,
        password = user.password,
        birthday = user.birthday,
        position = user.position,
        company = user.company,
        phone = user.phone,
        role = user.role ?: USER
    )

    fun userRequestDtoToUser(userRequestDto: UserRequestDto) = User(
        name = userRequestDto.name,
        lastname = userRequestDto.lastname,
        email = userRequestDto.email,
        password = userRequestDto.password,
        birthday = userRequestDto.birthday,
        position = userRequestDto.position,
        company = userRequestDto.company,
        phone = userRequestDto.phone,
        role = userRequestDto.role ?: USER
    )

    fun userToUserResponse(user: User ) = UserResponse(
        name = user.name,
        lastname = user.lastname,
        email = user.email,
        password = user.password,
        birthday = user.birthday,
        position = user.position,
        company = user.company,
        phone = user.phone,
        role = user.role ?: USER
    )
}
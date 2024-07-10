package com.calcpro.flowmaster.mapper

import com.calcpro.flowmaster.dao.entity.Role.ENGINEER
import com.calcpro.flowmaster.dao.entity.Engineer
import com.calcpro.flowmaster.dto.EngineerRequestDto
import com.calcpro.flowmaster.dto.EngineerResponse
import org.springframework.stereotype.Component

@Component
class EngineerMapper {
    fun engineerToEngineerRequestDto(engineer: Engineer) = EngineerRequestDto(
        name = engineer.name,
        lastname = engineer.email,
        email = engineer.email,
        password = engineer.password,
        birthday = engineer.birthday,
        position = engineer.position,
        company = engineer.company,
        phone = engineer.phone,
        role = engineer.role ?: ENGINEER
    )

    fun engineerRequestDtoToEngineer(engineerRequestDto: EngineerRequestDto) = Engineer(
        name = engineerRequestDto.name,
        lastname = engineerRequestDto.lastname,
        email = engineerRequestDto.email,
        password = engineerRequestDto.password,
        birthday = engineerRequestDto.birthday,
        position = engineerRequestDto.position,
        company = engineerRequestDto.company,
        phone = engineerRequestDto.phone,
        role = engineerRequestDto.role ?: ENGINEER
    )

    fun engineerToEngineerResponse(engineer: Engineer) = EngineerResponse(
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
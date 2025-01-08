package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.repository.UserRepository
import com.calcpro.flowmaster.dto.UserRequestDto
import com.calcpro.flowmaster.dto.UserResponse
import com.calcpro.flowmaster.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) {
    fun getEngineerById(id: Long): UserResponse {
        val engineer = userRepository.findById(id)
        return userMapper.userToUserResponse(engineer.get())
    }

    fun addNewEngineer(engineerRequest: UserRequestDto) {
        val engineer = userMapper.userRequestDtoToUser(engineerRequest)
        userRepository.save(engineer)
    }
}
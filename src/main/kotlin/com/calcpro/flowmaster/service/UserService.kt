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
    fun getUserById(id: Long): UserResponse {
        val user = userRepository.findById(id)
        return userMapper.userToUserResponse(user.get())
    }

    fun addNewUser(userRequest: UserRequestDto) {
        val user = userMapper.userRequestDtoToUser(userRequest)
        userRepository.save(user)
    }
}
package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.entity.AppUser
import com.calcpro.flowmaster.dao.repository.AppUserRepository
import com.calcpro.flowmaster.mapper.UserMapper
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val appUserRepository: AppUserRepository,
    private val userMapper: UserMapper
) {

    fun createUser(oAuth2User: OAuth2User) = AppUser(
        name = oAuth2User.getAttribute("name"),
        email = oAuth2User.getAttribute("email"),
    )
}
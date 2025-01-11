package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.entity.AppUser
import com.calcpro.flowmaster.dao.repository.AppUserRepository
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomOAuth2AppUserService(
    private val appUserRepository: AppUserRepository
) : DefaultOAuth2UserService() {

    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)

        // Extract user details
        val attributes = oAuth2User.attributes
        val googleId = attributes["sub"] as String
        val name = attributes["name"] as String
        val email = attributes["email"] as String

        // Save or update the user
        val user = appUserRepository.findById(googleId).orElseGet {
            AppUser(id = googleId, name = name, email = email)
        }.apply {
            this.name = name
            this.email = email
        }
        appUserRepository.save(user)

        return DefaultOAuth2User(oAuth2User.authorities, attributes, "sub")
    }
}

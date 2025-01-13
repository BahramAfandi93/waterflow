package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.entity.AppUser
import com.calcpro.flowmaster.dao.repository.AppUserRepository
import com.calcpro.flowmaster.logger.DPLogger
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomOAuth2AppUserService(
    private val appUserRepository: AppUserRepository
) : DefaultOAuth2UserService() {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)

        // Extract user details from Google
        val attributes = oAuth2User.attributes
        val googleId = attributes["sub"] as String
        val name = attributes["name"] as String
        val email = attributes["email"] as String

        // Save or update user in DB
        val user = appUserRepository.findById(googleId).orElseGet {
            log.info("ActionLog.loadUser: user not found by googleId: $googleId")
            AppUser(id = googleId, name = name, email = email)
        }/*.apply {
            log.info("Creating new user with Google ID: $googleId")
            this.name = name
            this.email = email
        }*/
        appUserRepository.save(user)
        log.info("ActionLog.loadUser: user saved: $user")

        return DefaultOAuth2User(oAuth2User.authorities, attributes, "sub")
    }
}

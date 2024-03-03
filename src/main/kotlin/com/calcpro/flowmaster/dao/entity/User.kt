package com.construe.waterflowcalc.dao.entity

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import org.hibernate.annotations.CreationTimestamp

data class User(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var lastname: String? = null,
    var username: String? = null,
    var email: String? = null,
    var password: String? = null,
    var birthday: LocalDate? = null,
    var position: String? = null,
    var companyName: String? = null,
    var phoneNumber: String? = null,

    @CreationTimestamp
    var userRegistrationDate: LocalDateTime? = null,
    var isEnabled: Boolean = false,
    var credentialsNonExpired: Boolean = false,
    var isAccountNonLocked: Boolean = false,
    var isAccountNonExpired: Boolean = false
)
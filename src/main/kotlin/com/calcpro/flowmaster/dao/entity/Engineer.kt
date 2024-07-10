package com.calcpro.flowmaster.dao.entity

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "engineer")
class Engineer(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var name: String,
    var lastname: String,
    var email: String,
    var password: String,
    var birthday: LocalDate,
    var position: String? = null,
    var company: String? = null,
    var phone: String? = null,
    var role: Role? = null,

    @OneToMany(mappedBy = "engineer", cascade = [CascadeType.ALL])
    val projects: List<Project>? = null,

    @CreationTimestamp
    var createdAt: LocalDateTime? = null,
//    var isEnabled: Boolean = false,
//    var credentialsNonExpired: Boolean = false,
//    var isAccountNonLocked: Boolean = false,
//    var isAccountNonExpired: Boolean = false,
)
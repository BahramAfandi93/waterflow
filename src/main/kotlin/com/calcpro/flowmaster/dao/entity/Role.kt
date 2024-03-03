package com.construe.waterflowcalc.dao.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

data class Role(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var roleId: Long? = null,
    var role: String? = null
)
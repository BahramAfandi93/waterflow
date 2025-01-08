package com.calcpro.flowmaster.dto

import com.calcpro.flowmaster.dao.entity.AppUser

data class ProjectRequest(
    val projectName: String,
    val projectLocation: String,
    val engineerId: Long? = null
)

class ProjectResponse(
    val projectName: String,
    val projectLocation: String,
    val engineer: AppUser
)
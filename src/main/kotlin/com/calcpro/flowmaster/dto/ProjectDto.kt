package com.calcpro.flowmaster.dto

import com.calcpro.flowmaster.dao.entity.User

data class ProjectRequest(
    val projectName: String,
    val projectLocation: String,
    val userId: Long? = null
)

class ProjectResponse(
    val projectName: String,
    val projectLocation: String,
    val user: User
)
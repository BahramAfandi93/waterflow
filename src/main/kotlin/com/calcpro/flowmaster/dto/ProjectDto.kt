package com.calcpro.flowmaster.dto

import com.calcpro.flowmaster.dao.entity.Customer

data class ProjectRequest(
    val projectName: String,
    val projectLocation: String,
    val customerId: Long? = null
)

class ProjectResponse(
    val projectName: String,
    val projectLocation: String,
    val customer: Customer
)
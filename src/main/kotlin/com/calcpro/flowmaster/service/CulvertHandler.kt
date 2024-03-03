package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse

interface CulvertHandler {
    fun structureShape(): String
    fun culvertInit(culvertRequest: CulvertRequest): CulvertResponse
    fun addPipe(culvertRequest: CulvertRequest): CulvertResponse
}
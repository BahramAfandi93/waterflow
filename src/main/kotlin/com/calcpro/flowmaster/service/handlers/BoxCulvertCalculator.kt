package com.calcpro.flowmaster.service.handlers

import com.calcpro.flowmaster.dao.entity.StructureShape.BOX_CULVERT
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import com.calcpro.flowmaster.service.CulvertHandler

class BoxCulvertCalculator: CulvertHandler {
    override fun structureShape() = BOX_CULVERT.type

    override fun culvertInit(culvertRequest: CulvertRequest): CulvertResponse {
        println("BURDAYAM BOXXXXX")
        return CulvertResponse(
            flowRate = 1244.3,
            requiredFlowRate = 54332.45,
            result = "OK"
        )
    }

    override fun addPipe(culvertRequest: CulvertRequest): CulvertResponse {
        TODO("Not yet implemented")
    }
}
package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dto.CulvertRequest
import org.springframework.stereotype.Component

@Component
class CulvertHandlerFactory(culvertHandlers: List<CulvertHandler>) {
    private val culvertHandlerMap = culvertHandlers.associateBy {
        it.structureShape()
    }

    fun getHandler(culvertRequest: CulvertRequest): CulvertHandler? {
        val structureShape = culvertRequest.shape.type
        return culvertHandlerMap[structureShape]
    }
}
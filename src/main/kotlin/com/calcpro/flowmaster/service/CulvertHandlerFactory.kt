package com.calcpro.flowmaster.service

import org.springframework.stereotype.Component

@Component
class CulvertHandlerFactory(culvertHandlers: List<CulvertHandler>) {
    private val culvertHandlerMap = culvertHandlers.associateBy {
        it.structureShape()
    }

    fun getHandler(structureShape: String): CulvertHandler? {
        return culvertHandlerMap[structureShape]
    }
}
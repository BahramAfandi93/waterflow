package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.logger.DPLogger
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import org.springframework.stereotype.Service

@Service
class ReceiveCulvertService(
    private val culvertHandlerFactory: CulvertHandlerFactory,
) {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    fun receiveCulvert(culvertRequest: CulvertRequest): CulvertResponse? {

        val handler = culvertHandlerFactory.getHandler(culvertRequest.shape.type)

        log.debug("ActionLog.ReceiveCulvertService.receiveCulvert: handler chosen -> {}", handler)

        val response = handler?.culvertInit(culvertRequest)

        log.debug("ActionLog.ReceiveCulvertService.receiveCulvert: culvert response received -> {}", response)

        return response
    }

}
package com.calcpro.flowmaster.service

import az.pashabank.ips.integration.logger.DPLogger
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import com.calcpro.flowmaster.mapper.CulvertMapper
import org.springframework.stereotype.Service

@Service
class ReceiveCulvertService(
    private val culvertMapper: CulvertMapper,
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
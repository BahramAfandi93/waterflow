package com.calcpro.flowmaster.controller

import az.pashabank.ips.integration.logger.DPLogger
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import com.calcpro.flowmaster.service.ReceiveCulvertService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/culvert")
class CulvertController(
    private val receiveCulvertService: ReceiveCulvertService
) {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    @PostMapping("/save")
    fun calculateCircleCulvert(@RequestBody culvertRequest: CulvertRequest): CulvertResponse? {
        log.debug(
            "ActionLog.CulvertController.calculateCircleCulvert:  request sent to controller -> {}", culvertRequest
        )
        return receiveCulvertService.receiveCulvert(culvertRequest)
    }
}
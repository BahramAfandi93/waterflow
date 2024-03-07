package com.calcpro.flowmaster.controller

import com.calcpro.flowmaster.logger.DPLogger
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import com.calcpro.flowmaster.service.CulvertService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/culvert")
class CulvertController(
    private val receiveCulvertService: CulvertService
) {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    @PostMapping("/save")
    fun createCulvert(@RequestBody culvertRequest: CulvertRequest): CulvertResponse? {
        log.debug(
            "ActionLog.CulvertController.createCulvert:  request sent to controller -> {}", culvertRequest
        )
        return receiveCulvertService.receiveCulvert(culvertRequest)
    }
}
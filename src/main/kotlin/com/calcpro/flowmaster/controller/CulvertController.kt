package com.calcpro.flowmaster.controller

import com.calcpro.flowmaster.dao.repository.CulvertRepository
import com.calcpro.flowmaster.dao.repository.ProjectRepository
import com.calcpro.flowmaster.logger.DPLogger
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import com.calcpro.flowmaster.mapper.CulvertMapper
import com.calcpro.flowmaster.service.CulvertService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/culvert")
class CulvertController(
    private val projectRepository: ProjectRepository,
    private val culvertMapper: CulvertMapper,
    private val receiveCulvertService: CulvertService,
    private val culvertRepository: CulvertRepository
) {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    @PostMapping("/save")
    fun createCulvert(
        @RequestParam projectId: Long,
        @RequestBody culvertRequest: CulvertRequest
    ): CulvertResponse? {

        log.debug(
            "ActionLog.CulvertController.createCulvert:  request sent to controller -> {}", culvertRequest
        )

        val project = projectRepository.findById(projectId).get()

        culvertRequest.projectId = projectId

        return receiveCulvertService.receiveCulvert(culvertRequest)
    }
}
package com.calcpro.flowmaster.controller

import com.calcpro.flowmaster.dto.ProjectRequest
import com.calcpro.flowmaster.logger.DPLogger
import com.calcpro.flowmaster.service.ProjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/project")
class ProjectController(
    private val projectService: ProjectService
) {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    @PostMapping("/add-new-project")
    fun saveNewProject(
        @RequestParam userId: Long,
        @RequestBody projectRequest: ProjectRequest
    ) = projectService.addNewProject(userId, projectRequest)

    @GetMapping("/get-project/id/{id}")
    fun getProjectById(@PathVariable id: Long) = projectService.getProjectById(id)

}
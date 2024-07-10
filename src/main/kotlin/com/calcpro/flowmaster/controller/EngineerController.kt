package com.calcpro.flowmaster.controller

import com.calcpro.flowmaster.dto.EngineerRequestDto
import com.calcpro.flowmaster.logger.DPLogger
import com.calcpro.flowmaster.service.EngineerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/engineer")
class EngineerController(
    private val engineerService: EngineerService
) {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    @PostMapping("/add-new-engineer")
    fun saveNewEngineer(@RequestBody engineerRequest: EngineerRequestDto) = engineerService.addNewEngineer(engineerRequest)

    @GetMapping("/get-engineer/id/{id}")
    fun getEngineerById(@PathVariable id: Long) = engineerService.getEngineerById(id)
}
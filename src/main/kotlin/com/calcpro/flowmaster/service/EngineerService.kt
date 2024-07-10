package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.repository.EngineerRepository
import com.calcpro.flowmaster.dto.EngineerRequestDto
import com.calcpro.flowmaster.dto.EngineerResponse
import com.calcpro.flowmaster.mapper.EngineerMapper
import org.springframework.stereotype.Service

@Service
class EngineerService(
    private val engineerRepository: EngineerRepository,
    private val engineerMapper: EngineerMapper
) {
    fun getEngineerById(id: Long): EngineerResponse {
        val engineer = engineerRepository.findById(id)
        return engineerMapper.engineerToEngineerResponse(engineer.get())
    }

    fun addNewEngineer(engineerRequest: EngineerRequestDto) {
        val engineer = engineerMapper.engineerRequestDtoToEngineer(engineerRequest)
        engineerRepository.save(engineer)
    }
}
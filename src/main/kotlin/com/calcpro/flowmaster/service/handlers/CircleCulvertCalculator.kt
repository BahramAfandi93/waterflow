package com.calcpro.flowmaster.service.handlers

import az.pashabank.ips.integration.logger.DPLogger
import com.calcpro.flowmaster.dao.entity.StructureShape.CIRCLE_CULVERT
import com.calcpro.flowmaster.dao.repository.CulvertRepository
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import com.calcpro.flowmaster.mapper.CulvertMapper
import com.calcpro.flowmaster.service.CulvertHandler
import com.calcpro.flowmaster.util.CalcUtil
import org.springframework.stereotype.Service

@Service
class CircleCulvertCalculator(
    private val culvertRepository: CulvertRepository,
    private val culvertMapper: CulvertMapper,
    private val calcUtil: CalcUtil
) : CulvertHandler {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    override fun structureShape() = CIRCLE_CULVERT.type

    override fun culvertInit(culvertRequest: CulvertRequest): CulvertResponse {
        log.info("ActionLog.CircleCulvertCalculator.culvertInit: request received")

        var entity = culvertMapper.culvertRequestToCulvertEntity(culvertRequest)

        entity = calcUtil.circleCulvertCalculationSetter(entity)

        log.info("ActionLog.CircleCulvertCalculator.culvertInit: entity created")

        log.debug("ActionLog.CircleCulvertCalculator.culvertInit: necessary fields calculated -> {}", entity)

        val responseEntity = culvertRepository.save(entity)

        log.debug("ActionLog.CircleCulvertCalculator.culvertInit: entity saved -> {}", responseEntity)

        return culvertMapper.culvertEntityToCulvertResponse(responseEntity)
    }

    override fun addPipe(culvertRequest: CulvertRequest): CulvertResponse {
        TODO("Not yet implemented")
    }

}
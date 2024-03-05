package com.calcpro.flowmaster.service.handlers

import az.pashabank.ips.integration.logger.DPLogger
import com.calcpro.flowmaster.dao.entity.StructureShape.BOX_CULVERT
import com.calcpro.flowmaster.dao.repository.CulvertRepository
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import com.calcpro.flowmaster.mapper.CulvertMapper
import com.calcpro.flowmaster.service.CulvertHandler
import com.calcpro.flowmaster.util.CalcUtil
import org.springframework.stereotype.Service

@Service
class BoxCulvertCalculator(
    private val culvertRepository: CulvertRepository,
    private val culvertMapper: CulvertMapper,
    private val calcUtil: CalcUtil
) : CulvertHandler {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    override fun structureShape() = BOX_CULVERT.type

    override fun culvertInit(culvertRequest: CulvertRequest): CulvertResponse {

        log.info("ActionLog.BoxCulvertCalculator.culvertInit: request received")

        var entity = culvertMapper.culvertRequestToCulvertEntity(culvertRequest)
        entity = calcUtil.boxCulvertCalculationSetter(entity)

        val responseEntity = culvertRepository.save(entity)

        return culvertMapper.culvertEntityToCulvertResponse(responseEntity)
    }

    override fun addPipe(culvertRequest: CulvertRequest): CulvertResponse {
        TODO("Not yet implemented")
    }
}
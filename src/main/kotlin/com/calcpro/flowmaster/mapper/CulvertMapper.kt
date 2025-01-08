package com.calcpro.flowmaster.mapper

import com.calcpro.flowmaster.dao.entity.Structure
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import org.apache.kafka.common.requests.DeleteAclsResponse.log
import org.springframework.stereotype.Component

@Component
class CulvertMapper {

    fun culvertRequestToCulvertEntity(payload: CulvertRequest): Structure {

        log.info("ActionLog.CulvertMapper.culvertRequestToCulvertEntity -> {}", payload)

        return Structure(
            chainage = payload.chainage,
            material = payload.material,
            flowHeight = payload.flowHeight,
            rainIntensity = payload.rainIntensity,
            calculationArea = payload.calculationArea,
            slope = payload.slope,
            shape = payload.shape,
            structureDiameter = payload.structureDiameter,
            structureWidth = payload.structureWidth,
            structureHeight = payload.structureHeight
        )
    }

    fun culvertEntityToCulvertResponse(entity: Structure): CulvertResponse {
        return CulvertResponse(
            centralAngle = entity.centralAngle,
            flowArea = entity.flowArea,
            waterSpeed = entity.waterSpeed,
            minAllowedSlope = entity.minAllowedSlope,
            hydraulicRadius = entity.hydraulicRadius,
            roughness = entity.roughness,
            wettedPerimeter = entity.wettedPerimeter,
            flowRate = entity.flowRate,
            requiredFlowRate = entity.requiredFlowRate,
            result = entity.result
        )
    }

}
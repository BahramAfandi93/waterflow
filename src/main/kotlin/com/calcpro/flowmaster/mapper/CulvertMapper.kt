package com.calcpro.flowmaster.mapper

import com.calcpro.flowmaster.dao.entity.CulvertEntity
import com.calcpro.flowmaster.dto.CulvertRequest
import com.calcpro.flowmaster.dto.CulvertResponse
import org.springframework.stereotype.Component

@Component
class CulvertMapper {

    fun culvertRequestToCulvertEntity(payload: CulvertRequest): CulvertEntity {
        return CulvertEntity(
            projectName = payload.project.projectName,
            location = payload.project.location,
            chainage = payload.chainage,
            material = payload.material,
            structureDiameter = payload.structureDiameter,
            structureWidth = payload.structureWith,
            structureHeight = payload.structureHeight,
            shape = payload.shape,
            slope = payload.slope,
            calculationArea = payload.calculationArea,
            flowHeight = payload.flowHeight,
            rainIntensity = payload.rainIntensity
        )
    }

    fun culvertEntityToCulvertResponse(entity: CulvertEntity): CulvertResponse {
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
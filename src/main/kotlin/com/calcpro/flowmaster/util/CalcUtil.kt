package com.calcpro.flowmaster.util

import com.calcpro.flowmaster.dao.entity.CulvertEntity
import com.calcpro.flowmaster.dao.entity.Result.FLOW_FAILED
import com.calcpro.flowmaster.dao.entity.Result.FLOW_IS_SATISFIED
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import org.apache.kafka.common.requests.DeleteAclsResponse.log
import org.springframework.stereotype.Component

@Component
class CalcUtil {
    fun culvertCalculationSetter(entity: CulvertEntity): CulvertEntity {
        entity.centralAngle = getCentralAngle(entity)
        entity.flowArea = getFlowArea(entity)
        entity.wettedPerimeter = getWettedPerimeter(entity)
        entity.waterSpeed = getWaterSpeed(entity)
        entity.hydraulicRadius = getHydraulicRadius(entity)
        entity.roughness = 0.014
        entity.flowRate = getFlowRate(entity)
        entity.requiredFlowRate = getRequiredFlowRate(entity)
        entity.result = getResult(entity)

        return entity
    }

    private fun getCentralAngle(entity: CulvertEntity): Double {
        val diameter = entity.structureDiameter!!
        val percent = entity.flowHeight

        log.info("ActionLog.CircleCulvertCalculator.getCentralAngle -> diameter = {}, percent = {}", diameter, percent)

        val radius = diameter / 2

        val height = if (percent < 50) {
            diameter * percent * 0.01
        } else {
            diameter - diameter * percent * 0.01
        }

        return 2 * acos((radius - height) / radius)
    }

    private fun getFlowArea(entity: CulvertEntity): Double {
        val diameter = entity.structureDiameter!!
        val percent = entity.flowHeight

        val angle = getCentralAngle(entity)
        val circularSegmentArea: Double = ((diameter / 2).pow(2.0) * (angle - sin(angle))) / 2
        val flowArea = if (percent < 50) {
            circularSegmentArea
        } else {
            Math.PI * (diameter / 2).pow(2.0) - circularSegmentArea
        }
        return flowArea
    }

    private fun getWettedPerimeter(entity: CulvertEntity): Double {
        val radius = entity.structureDiameter!! / 2
        val percent = entity.flowHeight
        val angle = getCentralAngle(entity)

        log.info("ActionLog.CircleCulvertCalculator.getWettedPerimeter -> {} , {}", radius, percent)

        val wettedPerimeter = if (percent < 50) {
            radius * angle
        } else {
            2 * Math.PI * radius - radius * angle
        }

        return wettedPerimeter
    }

    private fun getHydraulicRadius(entity: CulvertEntity): Double {

        log.info("Getting hydraulic radius")

        return getFlowArea(entity) / getWettedPerimeter(entity)
    }

    private fun getMinimumSlope(diameter: Double): Double {
        TODO("WILL BE CALCULATED FOR FUTURE")
    }

//    private fun getValueN(material: String?): Double {
//
//        log.info("Getting value N")
//
//        return when (material) {
//            "REINFORCED_CONCRETE" -> REINFORCED_CONCRETE.roughness
//            "CORRUGATED_METAL" -> 0.0015
//            "BRICK" -> 0.0016
//            "CERAMIC" -> 0.0017
//            "ASBESTOS_CEMENT" -> 0.0018
//            "cugun" -> 0.0019
//            "STEEL" -> 0.015
//            "PVC" -> 0.0021
//            "asfaltbeton" -> 0.0022
//            "torpaqkanal" -> 0.0023
//            "cinqilkanal" -> 0.0024
//            else -> 0.00
//        }
//    }

    private fun getWaterSpeed(entity: CulvertEntity): Double {
        val slope = entity.slope

        log.info(
            "ActionLog.CalcUtil.getWateerSpeed: calculation of water speed"
        )

        val rad = getHydraulicRadius(entity)
//        val valueN = material.let { Roughness.valueOf(it) }

        val powValue = 0.666 - 0.014 * sqrt(rad) //0.014 - the roughness should be calculated in future
        return 71.4 * rad.pow(powValue) * sqrt(slope / 100)
    }

    private fun getFlowRate(entity: CulvertEntity): Double {
        val flowArea = entity.flowArea!!
        val waterSpeed = entity.waterSpeed!!

        log.info("ActionLog.CalcUtil.getFlowRate: Getting flow rate")

        return flowArea * waterSpeed * 1000
    }

    private fun getRequiredFlowRate(entity: CulvertEntity): Double {
        val rainIntensity = entity.rainIntensity
        val calculationArea = entity.calculationArea

        log.info("ActionLog.CalcUtil.getRequiredFlowRate: Getting required flow rate")

        return rainIntensity * calculationArea!!
    }

    private fun getResult(entity: CulvertEntity): String {
        val flowRate = entity.flowRate!!
        val requiredFlowRate = entity.requiredFlowRate!!

        log.info("ActionLog.CalcUtil.getResult: result calculated")

        return if (flowRate > requiredFlowRate) {
            FLOW_IS_SATISFIED.toString()
        } else {
            FLOW_FAILED.toString()
        }
    }
}
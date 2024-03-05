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
    fun circleCulvertCalculationSetter(entity: CulvertEntity): CulvertEntity {
        entity.centralAngle = getCentralAngle(entity)
        entity.flowArea = getCircleFlowArea(entity)
        entity.wettedPerimeter = getCircleWettedPerimeter(entity)
        entity.waterSpeed = getCircleWaterSpeed(entity)
        entity.hydraulicRadius = getCircleHydraulicRadius(entity)
        entity.roughness = 0.014
        entity.flowRate = getFlowRate(entity)
        entity.requiredFlowRate = getRequiredFlowRate(entity)
        entity.result = getResult(entity)

        return entity
    }

    fun boxCulvertCalculationSetter(entity: CulvertEntity): CulvertEntity {
        log.info("boxCulvertRequest Entity accepted -> {}", entity.toString())
        entity.flowArea = getBoxFlowArea(entity)
        entity.wettedPerimeter = getBoxWettedPerimeter(entity)
        entity.waterSpeed = getBoxWaterSpeed(entity)
        entity.hydraulicRadius = getBoxHydraulicRadius(entity)
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

        val centralAngle = 2 * acos((radius - height) / radius)

        return "%.2f".format(centralAngle).toDouble()
    }

    private fun getCircleFlowArea(entity: CulvertEntity): Double {
        val diameter = entity.structureDiameter!!
        val percent = entity.flowHeight

        val angle = getCentralAngle(entity)
        val circularSegmentArea: Double = ((diameter / 2).pow(2.0) * (angle - sin(angle))) / 2
        val flowArea = if (percent < 50) {
            circularSegmentArea
        } else {
            Math.PI * (diameter / 2).pow(2.0) - circularSegmentArea
        }
        return "%.2f".format(flowArea).toDouble()
    }

    private fun getBoxFlowArea(entity: CulvertEntity): Double {
        val width = entity.structureWidth!!
        val height = entity.structureHeight!!
        val boxFlowArea = "%.2f".format(width * height * entity.flowHeight / 100).toDouble()

        log.info(
            "ActionLog.CalcUtil.getBoxFlowArea: width = {}, height = {}, boxFlowArea = {}",
            width,
            height,
            boxFlowArea
        )

        return "%.2f".format(boxFlowArea).toDouble()
    }

    private fun getCircleWettedPerimeter(entity: CulvertEntity): Double {
        val radius = entity.structureDiameter!! / 2
        val percent = entity.flowHeight
        val angle = getCentralAngle(entity)

        log.info("ActionLog.CalcUtil.getCircleWettedPerimeter -> {} , {}", radius, percent)

        val wettedPerimeter = if (percent < 50) {
            radius * angle
        } else {
            2 * Math.PI * radius - radius * angle
        }

        return "%.2f".format(wettedPerimeter).toDouble()
    }

    private fun getBoxWettedPerimeter(entity: CulvertEntity): Double {
        val width = entity.structureWidth!!
        val height = entity.structureHeight!!
        val boxWettedPerimeter = 2 * height + width

        log.info("Getting boxWettedPerimeter: -> {}", boxWettedPerimeter)

        return "%.2f".format(boxWettedPerimeter).toDouble()
    }

    private fun getCircleHydraulicRadius(entity: CulvertEntity): Double {

        log.info("Getting circleCulvert hydraulic radius: Start")

        val circleCulvertHydraulicRadius = getCircleFlowArea(entity) / getCircleWettedPerimeter(entity)

        log.info("Getting circleCulvert hydraulic radius: End -> {}", circleCulvertHydraulicRadius)

        return "%.2f".format(circleCulvertHydraulicRadius).toDouble()
    }

    private fun getBoxHydraulicRadius(entity: CulvertEntity): Double {

        log.info("Getting box hydraulic radius: Start")

        val boxHydraulicRadius = getBoxFlowArea(entity) / getBoxWettedPerimeter(entity)

        log.info("Getting box hydraulic radius: End")

        return "%.2f".format(boxHydraulicRadius).toDouble()
    }

    private fun getCircleWaterSpeed(entity: CulvertEntity): Double {
        val slope = entity.slope

        log.info("ActionLog.CalcUtil.getWateerSpeed: calculation of water speed")

        val rad = getCircleHydraulicRadius(entity)
//        val valueN = material.let { Roughness.valueOf(it) }

        val powValue = 0.666 - 0.014 * sqrt(rad) //0.014 - the roughness should be calculated in future

        val waterSpeed = 71.4 * rad.pow(powValue) * sqrt(slope / 100)

        return "%.2f".format(waterSpeed).toDouble()
    }

    private fun getBoxWaterSpeed(entity: CulvertEntity): Double {
        val slope = entity.slope

        log.info("ActionLog.CalcUtil.getWateerSpeed: calculation of water speed")

        val rad = getBoxHydraulicRadius(entity)
//        val valueN = material.let { Roughness.valueOf(it) }

        val powValue = 0.666 - 0.014 * sqrt(rad) //0.014 - the roughness should be calculated in future

        val waterSpeed = 71.4 * rad.pow(powValue) * sqrt(slope / 100)

        return "%.2f".format(waterSpeed).toDouble()
    }

    private fun getFlowRate(entity: CulvertEntity): Double {
        val flowArea = entity.flowArea!!
        val waterSpeed = entity.waterSpeed!!

        log.info("ActionLog.CalcUtil.getFlowRate: Getting flow rate")

        val flowRate = flowArea * waterSpeed * 1000

        return "%.2f".format(flowRate).toDouble()
    }

    private fun getRequiredFlowRate(entity: CulvertEntity): Double {
        val rainIntensity = entity.rainIntensity
        val calculationArea = entity.calculationArea

        log.info("ActionLog.CalcUtil.getRequiredFlowRate: Getting required flow rate")

        val requiredFlowRate = rainIntensity * calculationArea!!

        return "%.2f".format(requiredFlowRate).toDouble()
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


private fun getMinimumSlope(diameter: Double): Double {
    TODO("WILL BE CALCULATED FOR FUTURE")
}

private fun getValueN(material: String?): Double {
    TODO("WILL BE IMPLEMENTED FOR FUTURE")
//        log.info("Getting value N")

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
}
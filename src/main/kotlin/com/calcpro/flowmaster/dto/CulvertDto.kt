package com.calcpro.flowmaster.dto

import com.calcpro.flowmaster.dao.entity.StructureShape

data class CulvertRequest(
    var chainage: String,
    var material: String,
    var flowHeight: Int,
    var rainIntensity: Int,
    var calculationArea: Double,
    var slope: Double,
    var shape: StructureShape,
    var structureDiameter: Double? = null,
    var structureHeight: Double? = null,
    var structureWidth: Double? = null,
    var projectId: Long? = null
)

data class CulvertResponse(
    var centralAngle: Double? = null,
    var flowArea: Double? = null,
    var waterSpeed: Double? = null,
    var minAllowedSlope: Double? = null,
    var hydraulicRadius: Double? = null,
    var roughness: Double? = null,
    var wettedPerimeter: Double? = null,
    var flowRate: Double? = null,
    var requiredFlowRate: Double? = null,
    var result: String? = null
)
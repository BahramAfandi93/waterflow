package com.calcpro.flowmaster.dao.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "culvert_entity")
class CulvertEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var location: String,
    var projectName: String,
    var chainage: String,
    var material: String,
    var flowHeight: Int,
    var rainIntensity: Int,
    var calculationArea: Double? = null,
    var slope: Double,

    @Enumerated(STRING)
    var shape: StructureShape,
    var structureDiameter: Double? = null,
    var structureWidth: Double? = null,
    var structureHeight: Double? = null,
    var centralAngle: Double? = null,
    var waterSpeed: Double? = null,
    var wettedPerimeter: Double? = null,
    var flowArea: Double? = null,
    var hydraulicRadius: Double? = null,
    var minAllowedSlope: Double? = null,
    var roughness: Double? = null,
    var flowRate: Double? = null,
    var requiredFlowRate: Double? = null,
    var culvertPostDate: LocalDateTime? = null,
    var result: String? = null,

    @ManyToOne
    @JoinColumn(name = "project_entity_id")
    val project: ProjectEntity? = null
) {
    override fun toString(): String {
        return "CulvertEntity(id=$id, location='$location', projectName='$projectName', chainage='$chainage', material='$material', flowHeight=$flowHeight, rainIntensity=$rainIntensity, calculationArea=$calculationArea, slope=$slope, shape=$shape, structureDiameter=$structureDiameter, structureWidth=$structureWidth, structureHeight=$structureHeight, centralAngle=$centralAngle, waterSpeed=$waterSpeed, wettedPerimeter=$wettedPerimeter, flowArea=$flowArea, hydraulicRadius=$hydraulicRadius, minAllowedSlope=$minAllowedSlope, roughness=$roughness, flowRate=$flowRate, requiredFlowRate=$requiredFlowRate, culvertPostDate=$culvertPostDate, result=$result, project=$project)"
    }
}
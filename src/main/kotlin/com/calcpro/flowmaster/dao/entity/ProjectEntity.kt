package com.calcpro.flowmaster.dao.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "project")
class ProjectEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    val projectName: String,
    val projectLocation: String,

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
    val culverts: List<CulvertEntity>? = null
)
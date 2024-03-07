package com.calcpro.flowmaster.dao.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "project")
class Project(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    val projectName: String,
    val projectLocation: String,

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
    val culverts: List<Culvert>? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val customer: Customer? = null
)
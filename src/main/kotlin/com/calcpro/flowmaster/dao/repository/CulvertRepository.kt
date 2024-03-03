package com.calcpro.flowmaster.dao.repository

import com.calcpro.flowmaster.dao.entity.CulvertEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CulvertRepository: JpaRepository<CulvertEntity, Long> {
}
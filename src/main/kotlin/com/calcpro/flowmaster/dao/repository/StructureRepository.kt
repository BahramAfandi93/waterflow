package com.calcpro.flowmaster.dao.repository

import com.calcpro.flowmaster.dao.entity.Structure
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StructureRepository : JpaRepository<Structure, Long> {
}
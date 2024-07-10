package com.calcpro.flowmaster.dao.repository;

import com.calcpro.flowmaster.dao.entity.Engineer
import org.springframework.data.jpa.repository.JpaRepository

interface EngineerRepository : JpaRepository<Engineer, Long> {
}
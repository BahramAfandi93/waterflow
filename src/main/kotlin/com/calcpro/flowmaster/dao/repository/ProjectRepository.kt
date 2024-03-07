package com.calcpro.flowmaster.dao.repository;

import com.calcpro.flowmaster.dao.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long> {
}
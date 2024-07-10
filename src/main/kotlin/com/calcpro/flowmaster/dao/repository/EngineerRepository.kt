package com.calcpro.flowmaster.dao.repository;

import com.calcpro.flowmaster.dao.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface EngineerRepository : JpaRepository<User, Long> {
}
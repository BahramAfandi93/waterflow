package com.calcpro.flowmaster.dao.repository;

import com.calcpro.flowmaster.dao.entity.AppUser
import org.springframework.data.jpa.repository.JpaRepository

interface AppUserRepository : JpaRepository<AppUser, String>{
    fun findByEmail(email: String): AppUser?
}
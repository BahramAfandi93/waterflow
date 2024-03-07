package com.calcpro.flowmaster.dao.repository;

import com.calcpro.flowmaster.dao.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
}
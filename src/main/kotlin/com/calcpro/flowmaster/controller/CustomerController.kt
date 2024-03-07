package com.calcpro.flowmaster.controller

import com.calcpro.flowmaster.dto.CustomerRequestDto
import com.calcpro.flowmaster.logger.DPLogger
import com.calcpro.flowmaster.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController(
    private val customerService: CustomerService
) {

    companion object {
        private val log = DPLogger.getLogger(this::class.java)
    }

    @PostMapping("/add-new-customer")
    fun saveNewCustomer(@RequestBody customerRequest: CustomerRequestDto) = customerService.saveNewCustomer(customerRequest)

    @GetMapping("/get-customer/id/{id}")
    fun getCustomerById(@PathVariable id: Long) = customerService.getCustomerById(id)
}
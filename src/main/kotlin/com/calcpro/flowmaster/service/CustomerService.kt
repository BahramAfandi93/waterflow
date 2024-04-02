package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.repository.CustomerRepository
import com.calcpro.flowmaster.dto.CustomerRequestDto
import com.calcpro.flowmaster.dto.CustomerResponse
import com.calcpro.flowmaster.mapper.CustomerMapper
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val customerMapper: CustomerMapper
) {
    fun getCustomerById(id: Long): CustomerResponse {
        val customer = customerRepository.findById(id)
        return customerMapper.customerToCustomerResponse(customer.get())
    }

    fun addNewCustomer(customerRequest: CustomerRequestDto) {
        val customer = customerMapper.customerRequestDtoToCustomer(customerRequest)
        customerRepository.save(customer)
    }
}
package com.calcpro.flowmaster.service

import com.calcpro.flowmaster.dao.repository.CustomerRepository
import com.calcpro.flowmaster.dto.CustomerRequestDto
import com.calcpro.flowmaster.mapper.CustomerMapper
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val customerMapper: CustomerMapper
) {
    fun getCustomerById(id: Long): CustomerRequestDto {
        val customerRequestDto = customerRepository.findById(id)
        return customerMapper.customerToCustomerRequestDto(customerRequestDto.get())
    }

    fun saveNewCustomer(customerRequest: CustomerRequestDto) {
        val customer = customerMapper.customerRequestDtoToCustomer(customerRequest)
        customerRepository.save(customer)
    }
}
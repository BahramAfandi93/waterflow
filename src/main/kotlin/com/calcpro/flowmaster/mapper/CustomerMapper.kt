package com.calcpro.flowmaster.mapper

import com.calcpro.flowmaster.dao.entity.Customer
import com.calcpro.flowmaster.dao.entity.Role.USER
import com.calcpro.flowmaster.dto.CustomerRequestDto
import com.calcpro.flowmaster.dto.CustomerResponse
import org.springframework.stereotype.Component

@Component
class CustomerMapper {
    fun customerToCustomerRequestDto(customer: Customer) = CustomerRequestDto(
        name = customer.name,
        lastname = customer.email,
        email = customer.email,
        password = customer.password,
        birthday = customer.birthday,
        position = customer.position,
        company = customer.company,
        phone = customer.phone,
        role = customer.role ?: USER
    )

    fun customerRequestDtoToCustomer(customerRequestDto: CustomerRequestDto) = Customer(
        name = customerRequestDto.name,
        lastname = customerRequestDto.lastname,
        email = customerRequestDto.email,
        password = customerRequestDto.password,
        birthday = customerRequestDto.birthday,
        position = customerRequestDto.position,
        company = customerRequestDto.company,
        phone = customerRequestDto.phone,
        role = customerRequestDto.role ?: USER
    )

    fun customerToCustomerResponse(customer: Customer ) = CustomerResponse(
        name = customer.name,
        lastname = customer.lastname,
        email = customer.email,
        password = customer.password,
        birthday = customer.birthday,
        position = customer.position,
        company = customer.company,
        phone = customer.phone,
        role = customer.role ?: USER
    )
}
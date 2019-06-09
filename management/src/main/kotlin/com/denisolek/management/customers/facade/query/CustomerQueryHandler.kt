package com.denisolek.management.customers.facade.query

import com.denisolek.management.customers.infrastructure.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerQueryHandler(val customerRepository: CustomerRepository) {

}

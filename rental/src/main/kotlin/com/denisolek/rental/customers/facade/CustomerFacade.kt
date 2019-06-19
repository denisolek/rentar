package com.denisolek.rental.customers.facade

import com.denisolek.rental.customers.infrastructure.CustomerRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerFacade(val repository: CustomerRepository) {
    fun existOrThrow(id: UUID) = repository.existOrThrow(id)
}
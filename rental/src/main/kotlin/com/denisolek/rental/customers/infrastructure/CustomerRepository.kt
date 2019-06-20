package com.denisolek.rental.customers.infrastructure

import com.denisolek.rental.customers.model.Customer
import com.denisolek.rental.infrastructure.findOne
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerRepository(val repository: CustomerEntityRepository) {
    fun save(customer: Customer): Customer {
        return repository.save(CustomerEntity(customer)).toDomainModel()
    }

    fun deleteOrThrow(id: UUID) {
        repository.delete(repository.findOne(id) ?: throw CustomerExceptions.CustomerNotFoundException())
    }

    fun existOrThrow(id: UUID): Boolean {
        if (repository.countById(id) > 0)
            return true
        else throw CustomerExceptions.CustomerNotFoundException()
    }
}
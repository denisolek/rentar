package com.denisolek.rental.customers.infrastructure

import com.denisolek.rental.customers.infrastructure.CustomerExceptions.CustomerNotFoundException
import com.denisolek.rental.customers.model.Customer
import com.denisolek.rental.infrastructure.findOne
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerRepository : JpaRepository<CustomerEntity, UUID> {
    fun countById(id: UUID): Int

    fun save(customer: Customer): Customer {
        return save(CustomerEntity(customer)).toDomainModel()
    }

    fun findByIdOrThrow(id: UUID): Customer {
        return findOne(id)?.toDomainModel() ?: throw CustomerNotFoundException()
    }

    fun removeIfExists(id: UUID) {
        delete(findOne(id) ?: throw CustomerNotFoundException())
    }

    fun existOrThrow(id: UUID): Boolean {
        if (countById(id) > 0)
            return true
        else throw CustomerNotFoundException()
    }
}
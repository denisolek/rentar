package com.denisolek.management.customers.infrastructure.config

import com.denisolek.management.customers.infrastructure.CustomerEntity
import com.denisolek.management.customers.infrastructure.CustomerEntityRepository
import com.denisolek.management.customers.infrastructure.CustomerExceptions
import com.denisolek.management.customers.model.Customer
import com.denisolek.management.infrastructure.findOne
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerRepository(private val repository: CustomerEntityRepository) {
    fun countByEmail(email: String): Int {
        return repository.countByEmail(email)
    }

    fun countByDrivingLicence(drivingLicence: String): Int {
        return repository.countByDrivingLicence(drivingLicence)
    }

    fun countByPassport(passport: String): Int {
        return repository.countByPassport(passport)
    }

    fun save(customer: Customer): Customer {
        return repository.save(CustomerEntity(customer)).toDomainModel()
    }

    fun findByIdOrThrow(id: UUID): Customer {
        return repository.findOne(id)?.toDomainModel() ?: throw CustomerExceptions.CustomerNotFoundException()
    }

    fun findAll(): List<Customer> {
        return repository.findAll().map { it.toDomainModel() }
    }
}
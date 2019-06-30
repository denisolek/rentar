package com.denisolek.management.customers.infrastructure.config

import com.denisolek.management.customers.infrastructure.CustomerEntity
import com.denisolek.management.customers.infrastructure.CustomerEntityRepository
import com.denisolek.management.customers.infrastructure.CustomerExceptions
import com.denisolek.management.customers.model.Customer
import com.denisolek.management.customers.model.value.DrivingLicence
import com.denisolek.management.customers.model.value.Email
import com.denisolek.management.customers.model.value.Passport
import com.denisolek.management.infrastructure.findOne
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerRepository(private val repository: CustomerEntityRepository) {
    fun save(customer: Customer): Customer {
        return repository.save(CustomerEntity(customer)).toDomainModel()
    }

    fun findByIdOrThrow(id: UUID): Customer {
        return repository.findOne(id)?.toDomainModel() ?: throw CustomerExceptions.CustomerNotFoundException()
    }

    fun findByEmail(email: Email): List<Customer> {
        return repository.findByEmail(email.value).map { it.toDomainModel() }
    }

    fun findByDrivingLicence(drivingLicence: DrivingLicence): List<Customer> {
        return repository.findByDrivingLicence(drivingLicence.value).map { it.toDomainModel() }
    }

    fun findByPassport(passport: Passport): List<Customer> {
        return repository.findByPassport(passport.value).map { it.toDomainModel() }
    }

    fun findAll(): List<Customer> {
        return repository.findAll().map { it.toDomainModel() }
    }
}
package com.denisolek.management.customers.infrastructure

import com.denisolek.management.customers.facade.CustomerExceptions.CustomerNotFoundException
import com.denisolek.management.infrastructure.findOne
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerRepository : JpaRepository<CustomerEntity, UUID> {
    fun countByEmail(email: String): Int
    fun countByDrivingLicence(drivingLicence: String): Int
    fun countByPassport(passport: String): Int

    @JvmDefault
    fun findByIdOrThrow(id: UUID): CustomerEntity {
        return findOne(id) ?: throw CustomerNotFoundException()
    }
}
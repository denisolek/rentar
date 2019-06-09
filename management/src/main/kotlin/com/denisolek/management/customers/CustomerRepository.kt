package com.denisolek.management.customers

import com.denisolek.management.customers.domain.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerRepository : JpaRepository<CustomerEntity, UUID> {
    fun countByEmail(email: String): Int
    fun countByDrivingLicence(drivingLicence: String): Int
    fun countByPassport(passport: String): Int
}
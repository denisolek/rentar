package com.denisolek.management.customers.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerEntityRepository : JpaRepository<CustomerEntity, UUID> {
    fun countByEmail(email: String): Int
    fun countByDrivingLicence(drivingLicence: String): Int
    fun countByPassport(passport: String): Int
}
package com.denisolek.management.customers.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerEntityRepository : JpaRepository<CustomerEntity, UUID> {
    fun findByEmail(email: String): List<CustomerEntity>
    fun findByDrivingLicence(email: String): List<CustomerEntity>
    fun findByPassport(email: String): List<CustomerEntity>
}
package com.denisolek.rental.customers.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerEntityRepository : JpaRepository<CustomerEntity, UUID> {
    fun countById(id: UUID): Int
}
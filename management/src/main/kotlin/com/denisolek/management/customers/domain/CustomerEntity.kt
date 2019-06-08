package com.denisolek.management.customers.domain

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customer")
class CustomerEntity(
    @Id
    val id: UUID,
    val firstName: String,
    val lastName: String,
    @Column(unique = true)
    val email: String,
    val phoneNumber: String,
    val birthDate: LocalDate,
    @Column(unique = true)
    val drivingLicence: String,
    @Column(unique = true)
    val passport: String,
    val createdAt: LocalDateTime
)
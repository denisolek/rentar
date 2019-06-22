package com.denisolek.management.customers.infrastructure

import com.denisolek.management.customers.model.value.*
import com.denisolek.management.customers.model.Customer
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
    val phoneCountryCode: Int,
    val phoneNationalNumber: Long,
    val birthDate: LocalDate,
    @Column(unique = true)
    val drivingLicence: String,
    @Column(unique = true)
    val passport: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    constructor(customer: Customer) : this(
        id = customer.id.value,
        firstName = customer.name.firstName,
        lastName = customer.name.lastName,
        email = customer.email.value,
        phoneCountryCode = customer.phoneNumber.countryCode,
        phoneNationalNumber = customer.phoneNumber.nationalNumber,
        birthDate = customer.birthDate.value,
        drivingLicence = customer.drivingLicence.value,
        passport = customer.passport.value,
        createdAt = customer.createdAt,
        updatedAt = customer.updatedAt
    )

    fun toDomainModel() = Customer(
        id = CustomerId(this.id),
        name = Name(this.firstName, this.lastName),
        email = Email(this.email),
        phoneNumber = PhoneNumber("+${this.phoneCountryCode}${this.phoneNationalNumber}"),
        birthDate = BirthDate(this.birthDate),
        drivingLicence = DrivingLicence(this.drivingLicence),
        passport = Passport(this.passport),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}
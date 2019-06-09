package com.denisolek.management.customers.facade.query

import com.denisolek.management.customers.facade.CustomerExceptions.*
import com.denisolek.management.customers.infrastructure.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerQueryHandler(val repository: CustomerRepository) {
    fun validateAdd(dto: AddCustomerValidate) {
        when {
            repository.countByEmail(dto.email) > 0 -> throw EmailAlreadyExistsException()
            repository.countByDrivingLicence(dto.drivingLicence) > 0 -> throw DrivingLicenceAlreadyExistsException()
            repository.countByPassport(dto.passport) > 0 -> throw PassportAlreadyExistsException()
        }
    }

    fun validateUpdate(dto: UpdateCustomerValidate, id: UUID) {
        val customer = repository.findByIdOrThrow(id)
        when {
            customer.email != dto.email && repository.countByEmail(dto.email) > 0 ->
                throw EmailAlreadyExistsException()
            customer.drivingLicence != dto.drivingLicence && repository.countByDrivingLicence(dto.drivingLicence) > 0 ->
                throw DrivingLicenceAlreadyExistsException()
            customer.passport != dto.passport && repository.countByPassport(dto.passport) > 0 ->
                throw PassportAlreadyExistsException()
        }
    }
}

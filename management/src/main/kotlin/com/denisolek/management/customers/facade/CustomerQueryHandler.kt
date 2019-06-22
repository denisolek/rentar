package com.denisolek.management.customers.facade

import com.denisolek.management.customers.facade.query.AddCustomerValidate
import com.denisolek.management.customers.facade.query.BaseCustomer
import com.denisolek.management.customers.facade.query.DetailedCustomer
import com.denisolek.management.customers.facade.query.UpdateCustomerValidate
import com.denisolek.management.customers.infrastructure.CustomerExceptions.*
import com.denisolek.management.customers.infrastructure.config.CustomerRepository
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
            customer.email.equals(dto.email) && repository.countByEmail(dto.email) > 0 ->
                throw EmailAlreadyExistsException()
            customer.drivingLicence.equals(dto.drivingLicence) && repository.countByDrivingLicence(dto.drivingLicence) > 0 ->
                throw DrivingLicenceAlreadyExistsException()
            customer.passport.equals(dto.passport) && repository.countByPassport(dto.passport) > 0 ->
                throw PassportAlreadyExistsException()
        }
    }

    fun fetchAll(): List<BaseCustomer> {
        return repository.findAll().map { BaseCustomer(it) }
    }

    fun fetchById(id: UUID): DetailedCustomer {
        return DetailedCustomer(repository.findByIdOrThrow(id))
    }
}

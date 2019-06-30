package com.denisolek.management.customers.facade

import com.denisolek.management.customers.facade.query.AddCustomerValidate
import com.denisolek.management.customers.facade.query.BaseCustomer
import com.denisolek.management.customers.facade.query.DetailedCustomer
import com.denisolek.management.customers.facade.query.UpdateCustomerValidate
import com.denisolek.management.customers.infrastructure.CustomerExceptions.*
import com.denisolek.management.customers.infrastructure.config.CustomerRepository
import com.denisolek.management.customers.model.value.CustomerId
import com.denisolek.management.customers.model.value.DrivingLicence
import com.denisolek.management.customers.model.value.Email
import com.denisolek.management.customers.model.value.Passport
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerQueryHandler(val repository: CustomerRepository) {
    fun validateAdd(dto: AddCustomerValidate) {
        when {
            repository.findByEmail(Email(dto.email)).isNotEmpty() ->
                throw EmailAlreadyExistsException()
            repository.findByDrivingLicence(DrivingLicence(dto.drivingLicence)).isNotEmpty() ->
                throw DrivingLicenceAlreadyExistsException()
            repository.findByPassport(Passport(dto.passport)).isNotEmpty() ->
                throw PassportAlreadyExistsException()
        }
    }

    fun validateUpdate(dto: UpdateCustomerValidate, id: UUID) {
        when {
            repository.findByEmail(Email(dto.email)).firstOrNull()?.id != CustomerId(id) ->
                throw EmailAlreadyExistsException()
            repository.findByDrivingLicence(DrivingLicence(dto.drivingLicence)).firstOrNull()?.id != CustomerId(id) ->
                throw DrivingLicenceAlreadyExistsException()
            repository.findByPassport(Passport(dto.passport)).firstOrNull()?.id != CustomerId(id) ->
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

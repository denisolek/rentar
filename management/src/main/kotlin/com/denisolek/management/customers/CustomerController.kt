package com.denisolek.management.customers

import com.denisolek.management.customers.domain.event.CustomerAdded
import com.denisolek.management.customers.domain.event.CustomerUpdated
import com.denisolek.management.customers.domain.value.CustomerId
import com.denisolek.management.customers.dto.AddCustomerDTO
import com.denisolek.management.customers.dto.CustomerExceptions.*
import com.denisolek.management.customers.dto.UpdateCustomerDTO
import com.denisolek.management.infrastructure.send
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.state.QueryableStoreTypes
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class CustomerController(
    val kafkaStreams: KafkaStreams,
    val customerRepository: CustomerRepository,
    val eventProducer: Producer<String, String>
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    companion object {
        const val CUSTOMER_ID = "customerId"
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCustomer(@RequestBody addCustomerDTO: AddCustomerDTO): UUID {
        addCustomerDTO.validate()
        addCustomerDTO.run { isUnique(this.email, this.drivingLicence, this.passport) }
        return CustomerAdded(addCustomerDTO).run {
            eventProducer.send(this)
            logger.info("[CustomerAdded] {${this.aggregateId}} - Sent")
            this.aggregateId
        }
    }

    @PutMapping("/customers/{$CUSTOMER_ID}")
    fun updateCustomer(
        @PathVariable(required = true, value = CUSTOMER_ID) id: UUID,
        @RequestBody updateCustomerDTO: UpdateCustomerDTO
    ) {
        updateCustomerDTO.validate()
        val customer = customerRepository.findByIdOrThrow(id)
        updateCustomerDTO.run { isUnique(this.email, this.drivingLicence, this.passport) }
        CustomerUpdated(updateCustomerDTO, CustomerId(id)).run {
            eventProducer.send(this)
            logger.info("[CustomerUpdated] {${this.aggregateId}} - Sent")
        }
    }

    @GetMapping("/customers")
    fun getCustomers() {
        val store = kafkaStreams.store("allEvents", QueryableStoreTypes.keyValueStore<String, String>())
        logger.info("test")
    }

    private fun isUnique(email: String, drivingLicence: String, passport: String) {
        when {
            customerRepository.countByEmail(email) > 0 -> throw EmailAlreadyExistsException()
            customerRepository.countByDrivingLicence(drivingLicence) > 0 -> throw DrivingLicenceAlreadyExistsException()
            customerRepository.countByPassport(passport) > 0 -> throw PassportAlreadyExistsException()
        }
    }
}
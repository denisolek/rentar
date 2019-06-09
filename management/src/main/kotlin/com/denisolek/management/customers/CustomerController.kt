package com.denisolek.management.customers

import com.denisolek.management.customers.domain.event.CustomerAdded
import com.denisolek.management.customers.dto.AddCustomerDTO
import com.denisolek.management.customers.dto.CustomerExceptions.*
import com.denisolek.management.infrastructure.send
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.state.QueryableStoreTypes
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(
    val kafkaStreams: KafkaStreams,
    val customerRepository: CustomerRepository,
    val eventProducer: Producer<String, String>
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    fun addCustomer(@RequestBody addCustomerDTO: AddCustomerDTO) {
        addCustomerDTO.validate()
        when {
            customerRepository.countByEmail(addCustomerDTO.email) > 0 -> throw EmailAlreadyExistsException()
            customerRepository.countByDrivingLicence(addCustomerDTO.email) > 0 -> throw DrivingLicenceAlreadyExistsException()
            customerRepository.countByPassport(addCustomerDTO.email) > 0 -> throw PassportAlreadyExistsException()
        }
        CustomerAdded(addCustomerDTO).run {
            eventProducer.send(this)
            logger.info("[CustomerAdded] {${this.aggregateId}} - Sent")
        }
    }

    @GetMapping("/customers")
    fun getCustomers() {
        val store = kafkaStreams.store("allEvents", QueryableStoreTypes.keyValueStore<String, String>())
        logger.info("test")
    }
}